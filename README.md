

# rg-archetype-alexa-aws-lambda

Amazon AWS Lambda function that can be used as a endpoint for a Alexa skill and ApiAi.

FYI [The usage of archetype and meaning I got from maven.](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)

## Technologies
* Eclipse 4.5.2 Mars
* Jdk1.8.0_77
* AWS Lambda Java support libraries
* Java Alexa Skills kit
* Amazon AWS
* Amazon Alexa
* Api.ai

## Get and build Jar Files

Note: these are already in the lib folder

### Aws

[Download AWS Lambda Java support libraries.](https://github.com/aws/aws-lambda-java-libs)
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

### ApiAi

[Download Java SDK for api.ai.](https://github.com/api-ai/apiai-java-client)
```
> cd {apiai_source_home}
> mvn package
```

### Gson

[Gson Java serialization/deserialization libary.](https://github.com/google/gson)
```
> cd {gson_source_home}
> mvn package
```

### Simple Logging Facade for Java

[Download, at the time 1.7.25 was latest.](https://www.slf4j.org/download.html)

## Create Archive

### Update Alexa Skill Application Id

To get your Alexa skill id, Go to Amazon Developer -> Alexa -> Alexa Skills Kit - Get Started -> select the link **View Skill ID**.  Go to **org.rg.archetype.aws.lambda.alexa.MainSpeechletRequestStreamHandler** and update the supported ids list in the class to contain you Alexa skill id.

### Build jar

```
> cd {rg-archetype-alexa-aws-lambda home}
> mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
or
> mvn package
```

## Alexa

### Create AWS Lambda Function

* Go to the AWS Console and click on the Lambda link. **Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.**
* Click **Create function** or **Get Started Now** button.
* Skip selecting a blueprint and click **Author from scratch** button.
* On add trigger, choose **Alexa Skills Kit** and select **Next**
* On configure function -> basic information - assign a name and for the runtime choose **Java 8**
* On configure function -> Lambda function code - for the code, upload {rg-archetype-alexa-aws-lambda home}\target\rg-archetype-aws-lambda-1.0.jar
* On configure function -> Lambda function handler and role - for the handler enter **org.rg.archetype.aws.lambda.alexa.MainSpeechletRequestStreamHandler**.
* On configure function -> Lambda function handler and role - for role, select **Create a custom role**, for IAM Role select **lambda_basic_excution** and choose **Allow**
* On configure function -> Lambda function handler and role - for existing role, select **lambda_basic_excution**
* Click **Next**
* Click **Create function**

### Alexa Skill Setup

The only thing I will cover here is configuring the endpoint.

#### Configuration tab

For 'Service Endpoint Type' choose **AWS Lambda ARN (Amazon Resource Name)**
For 'Default', go to the AWS console and copy the ARN for the Lambda function you just created (usually in the upper right corner of the console window)

## Api.ai

### Create AWS Lambda Function

* Go to the AWS Console and click on the Lambda link. **Note: ensure you are in us-east or you wont be able to use Alexa with Lambda.**
* Click **Create function** or **Get Started Now** button.
* Skip selecting a blueprint and click **Author from scratch** button.
* I did not add any triggers, select **Next**
* On configure function -> basic information - assign a name and for the runtime choose **Java 8**
* On configure function -> Lambda function code - for the code, upload {rg-archetype-alexa-aws-lambda home}\target\rg-archetype-aws-lambda-1.0.jar
* On configure function -> Lambda function handler and role - for the handler enter **org.rg.archetype.aws.lambda.apiai.MainRequestStreamHandler**.
* On configure function -> Lambda function handler and role - for role, select **Create a custom role**, for IAM Role select **lambda_basic_excution** and choose **Allow**
* On configure function -> Lambda function handler and role - for existing role, select **service-role/execute_my_lambda**
* Click **Next**
* Click **Create function**

### Build API to expose a Lambda Function

http://docs.aws.amazon.com/apigateway/latest/developerguide/getting-started.html

## Helpful Resources

### Create maven pom.xml and archive code

https://docs.aws.amazon.com/lambda/latest/dg/lambda-java-how-to-create-deployment-package.html
https://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-no-ide.html
https://github.com/amzn/alexa-skills-kit-java/blob/master/samples/pom.xml

### Alexa Skill and AWS Configuration

https://developer.amazon.com/alexa-skills-kit/alexa-skill-quick-start-tutorial

### Apiai

http://docs.aws.amazon.com/apigateway/latest/developerguide/api-gateway-create-api-as-simple-proxy-for-lambda.html#api-gateway-proxy-integration-lambda-function-java
https://www.raizlabs.com/dev/2017/01/build-ai-assistant-api-ai-amazon-lambda/
