# HOW TO RUN

## Docker

### Start application services

~~~bash
    docker-compose up -d
~~~

### Start application services

~~~bash
    docker-compose down -v
~~~

### Inactive delection protection

~~~bash
aws cognito-idp update-user-pool --user-pool-id **** --deletion-protection INACTIVE --auto-verified-attributes email
~~~
