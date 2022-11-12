# GUIDE

## Docker

### Build a image

~~~bash
    docker build . -t statistic-service:0.0.1
~~~

### Run a container

~~~bash
    docker run -d -p 9082:9082 statistic-service:0.0.1
~~~
