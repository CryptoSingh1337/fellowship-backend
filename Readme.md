# Fellowship Backend

### Sample data
```json
[
    {
        "firstName": "Josephine",
        "lastName": "Page",
        "username": "test_1",
        "email": "ipsum.ac@icloud.org",
        "password": "123456"
    },
    {
        "firstName": "Hayes",
        "lastName": "Contreras",
        "username": "test_2",
        "email": "diam.lorem.auctor@aol.net",
        "password": "123456"
    },
    {
        "firstName": "Xanthus",
        "lastName": "Mejia",
        "username": "test_3",
        "email": "egestas@icloud.ca",
        "password": "123456"
    },
    {
        "firstName": "Ashton",
        "lastName": "Travis",
        "username": "test_4",
        "email": "porttitor.eros.nec@icloud.ca",
        "password": "123456"
    },
    {
        "firstName": "Miriam",
        "lastName": "Rocha",
        "username": "test_5",
        "email": "faucibus.morbi@hotmail.com",
        "password": "123456"
    }
]
```

### Login - GET
Request:
```text
{{server}}/api/v1/login
```

Response:
```text
{
    "responseStatus": "SUCCESS",
    "data": {
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0XzEiLCJpc3MiOm51bGwsImV4cCI6MTY1OTYzMjE2OH0.02ImoQGNQKNlCohWxKstkjysUl12b6lpdPU3tskEaa4",
        "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0XzEiLCJpc3MiOm51bGwsImV4cCI6MTY1OTY3NTM2OH0.ENI2HtenhjSySffbnf--CIvmxdjPHh3AS4GDymTFHTc"
    },
    "error": null
}
```

### Register - POST
Request:
```text
{{server}}/api/v1/user/create
```
Payload:
```json
{
    "firstName": "Saransh",
    "lastName": "Kumar",
    "username": "test_6",
    "password": "123456",
    "email": "abc@xyz.com"
}
```

Response:
```text
{
    "responseStatus": "SUCCESS",
    "data": {
        "firstName": "Saransh",
        "lastName": "Kumar",
        "username": "test_3",
        "email": "abc@xyz.com"
    },
    "error": null
}
```
### Get user - GET
Request:
```shell
{{server}}/api/v1/user/{{username}}
```

Response:
```text
{
    "responseStatus": "SUCCESS",
    "data": {
        "firstName": "Josephine",
        "lastName": "Page",
        "username": "test_1",
        "email": "ipsum.ac@icloud.org"
    },
    "error": null
}
```

### Get all users - GET
Request:
```shell
{{server}}/api/v1/user/all
```

Response:
```text
{
    "responseStatus": "SUCCESS",
    "data": {
        "users": [
            {
                "firstName": "Josephine",
                "lastName": "Page",
                "username": "test_1",
                "email": "ipsum.ac@icloud.org"
            },
            {
                "firstName": "Hayes",
                "lastName": "Contreras",
                "username": "test_2",
                "email": "diam.lorem.auctor@aol.net"
            },
            {
                "firstName": "Xanthus",
                "lastName": "Mejia",
                "username": "test_3",
                "email": "egestas@icloud.ca"
            },
            {
                "firstName": "Ashton",
                "lastName": "Travis",
                "username": "test_4",
                "email": "porttitor.eros.nec@icloud.ca"
            },
            {
                "firstName": "Miriam",
                "lastName": "Rocha",
                "username": "test_5",
                "email": "faucibus.morbi@hotmail.com"
            }
        ]
    },
    "error": null
}
```