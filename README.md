

# rg-archetype-alexa-aws-lambda

Amazon AWS Lambda function that can be used as a endpoint for a Alexa skill.

FYI [The usage of archetype and meaning I got from maven.](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)

## Technologies
* Eclipse 4.5.2 Mars
* Jdk1.8.0_77
* AWS Lambda Java support libraries
* Java Alexa Skills kit
* Amazon AWS
* Amazon Alexa

## Get and build Jar Files

Note: these are already in the lib folder

### Aws

[Download AWS Lambda Java support libraries.]( https://github.com/aws/aws-lambda-java-libs)
Use maven to build java-core
```
> cd {aws_lamba_source_home}\aws-lambda-java-core
> mvn package
```

### Alexa

[Download Java Alexa Skills kit.](https://github.com/amzn/alexa-skills-kit-java)
```
> cd {alexa_source_home}
> mvn package
```

### Simple Logging Facade for Java

[Download, at the time 1.7.25 was latest.](https://www.slf4j.org/download.html)

## Create Archive

### Update Alexa Skill Application Id

To get your Alexa skill id, Go to Amazon Developer -> Alexa -> Alexa Skills Kit - Get Started -> select the link **View Skill ID**.  Go to **main.java.MainSpeechletRequestStreamHandler** and update the supported ids list in the class to contain you Alexa skill id.

### Build jar

```
> cd {rg-archetype-alexa-aws-lambda home}
> mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
```

## Create AWS Lambda Function

* Go to the AWS Console and click on the Lambda link. **Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.**
* Click **Create function** or **Get Started Now** button.
* Skip selecting a blueprint and click **Author from scratch** button.
* On add trigger, choose **Alexa Skills Kit** and select **Next**
* On configure function -> basic information - assign a name and for the runtime choose **Java 8**
* On configure function -> Lambda function code - for the code, upload {rg-archetype-alexa-aws-lambda home}\target\rg-archetype-alexa-aws-lambda-1.0.jar
* On configure function -> Lambda function handler and role - for the handler enter **main.java.MainSpeechletRequestStreamHandler**.  Note, if you had your java source code in a subpackage called, let's say, helloworld.  You would need to specifiy that in the path i.e. **main.java.helloworld.MainSpeechletRequestStreamHandler**
* On configure function -> Lambda function handler and role - for role, select **Create a custom role**, for IAM Role select **lambda_basic_excution** and choose **Allow**
* On configure function -> Lambda function handler and role - for existing role, select **lambda_basic_excution**
* Click **Next**
* Click **Create function**

## Alexa Skill Setup

The only thing I will cover here is configuring the endpoint.

### Configuration tab

For 'Service Endpoint Type' choose **AWS Lambda ARN (Amazon Resource Name)**
For 'Default', go to the AWS console and copy the ARN for the Lambda function you just created (usually in the upper right corner of the console window)

## Helpful Resources

### Create maven pom.xml and archive code

https://docs.aws.amazon.com/lambda/latest/dg/lambda-java-how-to-create-deployment-package.html
https://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-no-ide.html
https://github.com/amzn/alexa-skills-kit-java/blob/master/samples/pom.xml

### Alexa Skill and AWS Configuration

https://developer.amazon.com/alexa-skills-kit/alexa-skill-quick-start-tutorial
