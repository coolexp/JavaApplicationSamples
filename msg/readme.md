protoc -I=message --java_out=java message/AwesomeMessage.proto
pbjs -t static-module -w commonjs -o js/data.js awesome.proto

pom:
<!-- https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java -->
<dependency>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java</artifactId>
    <version>3.3.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.google.protobuf/protobuf-java-util -->
<dependency>
    <groupId>com.google.protobuf</groupId>
    <artifactId>protobuf-java-util</artifactId>
    <version>3.3.1</version>
</dependency>