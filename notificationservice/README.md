# GUIDE

## Docker

### Build a image

~~~bash
    docker build . -t notification-service:0.0.1
~~~

### Run a container

~~~bash
    docker run -d -p 9083:9083 notification-service:0.0.1
~~~
