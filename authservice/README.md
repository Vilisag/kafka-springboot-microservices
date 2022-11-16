# GUIDE

## Docker

### Build a image

~~~bash
    docker build . -t auth-service:0.0.1
~~~

### Run a container

~~~bash
    docker run -d -p 9086:9086 auth-service:0.0.1
~~~

## Key generation

~~~bash
    openssl genrsa -out keypair.pem 2048
    openssl rsa -in keypair.pem -pubout -out public.pem
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem
~~~