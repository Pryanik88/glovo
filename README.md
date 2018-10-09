# Instructions

> Glovo test full stack

>Important note:
You need to patch cacert file in your jdk folder {JDKPath}/jre/lib/security/ to connect to moneeda API.
Can find patched cacert file here in project root or patch it with keytool using their public certificate as described here:
https://stackoverflow.com/questions/6659360/how-to-solve-javax-net-ssl-sslhandshakeexception-error

## Build Setup

``` bash
#build front
cd frontend

npm install

npm run build

#build back

cd ..

mvn clean install

```
>Local server is on localhost:8080/ 
 

