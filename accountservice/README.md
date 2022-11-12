# GUIDE

## Docker

### Build a image

~~~bash
    docker build . -t account-service:0.0.1
~~~

### Run a container

~~~bash
    docker run -d -p 9080:9080 account-service:0.0.1
~~~
