protoc -I=message --java_out=java message/AwesomeMessage.proto
pbjs -t static-module -w commonjs -o js/data.js awesome.proto