# Fellowship Backend

![](https://img.shields.io/badge/Java-17-blue)
![](https://img.shields.io/badge/Spring%20Boot-2.6.10-70b34a)
![](https://img.shields.io/badge/Nuxt.js-2.15.8-2ddc87)
![](https://img.shields.io/badge/Vuetify.js-2.6.1-2e95f2)

A Scholarship Aggregator which updates in real time. It has the following functionality:

- Minimalistic UI
- Scrap scholarships from various sources.
- Admin dashboard where admin can update scholarships, user's data and can add scholarships from the portal itself using the JSON file.
- Sends notifications to the registered user about the recent scholarship which are added on the portal.
- Auto remove all the expired scholarships.
- Recommend scholarships based on user details (country, degree).
- User can search scholarships based on country, degree and phrases.

**Tech stack**:

- **Back-end -**
  - Java
  - Spring Boot
  - Spring Security
  - Spring Scheduler
  - Spring Data Mongo
  - Java Mail API
  - MongoDB
  - Amazon SQS
  - Heroku (Deployment)
- **Front-end -**
  - JavaScript
  - Nuxt.js (SSR and SSG)
  - Vuetify
  - Vercel (Deployment)

## User Endpoints

### GET - Login
Request:
```text
{{server}}/api/v1/login
```
Payload:
```json
{
    "username": "test_1",
    "password": "123456"
}
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "accessToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0XzEiLCJyb2xlcyI6WyJBRE1JTiIsIlVTRVIiXSwiaXNzIjoiRmVsbG93c2hpcCIsImV4cCI6MTY2MDYyOTA5Nn0.4KFefXfBOsyGEbzeyoVS-vIKDaiMcxAT8IUappFoze4",
        "refreshToken": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0XzEiLCJpc3MiOiJGZWxsb3dzaGlwIiwiZXhwIjoxNjYwNjcyMjk2fQ.3kUHmd_eKfWA8sPkhnULlJ5RHSoKFw-qWH4Emaz-O4k"
    },
    "error": null
}
```

### GET - Register
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
        "username": "test_6",
        "email": "abc@xyz.com"
    },
    "error": null
}
```
### GET - Get user
Request:
```text
{{server}}/api/v1/user/{{username}}
```

Response:
```json
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
> User can only request for its own username.

### GET - Get all users (Only for Role - ADMIN)
Request:
```text
{{server}}/api/v1/user/all
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "users": [
            {
                "firstName": "Josephine",
                "lastName": "Page",
                "username": "test_1",
                "email": "ipsum.ac@icloud.org",
                "country": null,
                "degree": null,
                "roles": [
                    "ADMIN",
                    "USER"
                ]
            },
            {
                "firstName": "Ashton",
                "lastName": "Travis",
                "username": "test_4",
                "email": "porttitor.eros.nec@icloud.ca",
                "country": "india",
                "degree": "BACHELOR",
                "roles": [
                    "USER"
                ]
            },
            {
                "firstName": "Xanthus",
                "lastName": "Mejia",
                "username": "test_3",
                "email": "egestas@icloud.ca",
                "country": null,
                "degree": null,
                "roles": [
                    "ADMIN",
                    "USER"
                ]
            },
            {
                "firstName": "Hayes",
                "lastName": "Contreras",
                "username": "test_2",
                "email": "diam.lorem.auctor@aol.net",
                "country": null,
                "degree": null,
                "roles": [
                    "ADMIN",
                    "USER"
                ]
            },
            {
                "firstName": "Miriam",
                "lastName": "Rocha",
                "username": "test_5",
                "email": "faucibus.morbi@hotmail.com",
                "country": "united states",
                "degree": "MASTER",
                "roles": [
                    "USER"
                ]
            },
            {
                "firstName": "Saransh",
                "lastName": "Kumar",
                "username": "test_6",
                "email": "abcd@xyz.com",
                "country": "india",
                "degree": "MASTER",
                "roles": [
                    "USER"
                ]
            }
        ]
    },
    "error": null
}
```

### Update user data - PUT
Request:
```text
{{server}}/api/v1/user/update
```
Payload:
```json
{
    "firstName": "Saransh",
    "lastName": "Kumar",
    "email": "abc@xyz.com",
    "country": "India",
    "degree": "MASTER"
}
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "firstName": "Saransh",
        "lastName": "Kumar",
        "email": "abcd@xyz.com",
        "country": "india",
        "degree": "MASTER"
    },
    "error": null
}
```

## Scholarship Endpoints

### GET - Get all Scholarships (No Auth token required)
Request:
```text
{{server}}/api/v1/scholarship/all?page=0
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "scholarships": [
            {
                "title": "International Students Support Contest 2022-2023",
                "url": "https://kidatschool.com/international-students-support-contest-2022-2023",
                "grant": "Up to $2,000 awards",
                "description": "Applications are now open for a scholarships contest programme for 2022-2023! Our goal is to provide financial assistance and mentorship to students without the necessary means who demonstrate alignment with our values and want to further their education for the 2022-2023 academic year.\n\nDo you have a special academic interest, or are you passionate about something on a practical or theoretical level? Would you like financial support for your research project, dissertation, project, or anything else? Then you should participate in our competition!",
                "deadline": "2023-06-30 00:00",
                "country": "india",
                "degrees": [
                    "BACHELOR",
                    "PHD"
                ],
                "createdAt": "2022-08-14T03:38:46.305"
            },
            {
                "title": "Human Resource (HR) - Free Course From Windsor University's MBA",
                "url": "https://windsoruniversity.us/academics/master-of-business-administration-mba/mba-curriculum/phri/",
                "grant": "100% free",
                "description": "In this course, you will learn how to use integrated coaching, organizational development, career planning, and counseling skills to design, manage, and evaluate plans that improve the individual’s productivity, employability, and job satisfaction, as well as organizational effectiveness, employment, management development, customer service, and quality management. This course includes psychology, structure behavior, adult education principles, activity counseling, ability testing and evaluation, program design, consulting practice, structure development, and applications to issues.",
                "deadline": "2022-05-14 00:00",
                "country": "india",
                "degrees": [
                    "MASTER"
                ],
                "createdAt": "2022-08-14T03:38:46.369"
            },
            {
                "title": "Women in Tech Scholarship: Up to 50% off your Tuition Fee on all of our Programmes",
                "url": "https://bit.ly/Women-in-Tech-Scholarship",
                "grant": "50% off tuition fees",
                "description": "We created the [Women in Tech Scholarship](https://bit.ly/Women-in-Tech-Scholarship), to do our part to build a world that is diverse, equitable, and inclusive.\n\nApply by March 31, 2022, to receive up to 50% off your tuition fee and a reduced application fee.\n\nLast year we awarded €412,000 to 25 students.\n\nOur programmes:\n\n* Data Science\n* Computer Science\n* Front-end Development\n* Cyber Security\n* Product Management\n* Fintech\n* Interaction Design\n* Digital Marketing\n* High-tech Entrepreneurship",
                "deadline": "2022-03-31 00:00",
                "country": "india",
                "degrees": [
                    "MASTER",
                    "PHD"
                ],
                "createdAt": "2022-08-14T03:38:46.372"
            },
            {
                "title": "Study Product Management with a 50% Scholarship",
                "url": "https://scholarships.harbour.space/product-management?utm_source=studyportals&utm_medium=listing&utm_campaign=both_b2c_pm-launch",
                "grant": "Get up to 11,450€ off your tuition fee",
                "description": "We’re searching for talented individuals to join the first batch of our Product Management programme.\n\n  \n\nIf you would like to kick-start your product career, don’t miss this opportunity!",
                "deadline": "2022-03-31 00:00",
                "country": "india",
                "degrees": [
                    "BACHELOR"
                ],
                "createdAt": "2022-08-14T03:38:46.376"
            }
        ]
    },
    "error": null
}
```

### POST - Create scholarships in bulk (Only for Role - ADMIN)
Request:
```text
{{server}}/api/v1/scholarship/create/bulk
```
Payload:
```json
[
    {
        "country": "INDIA",
        "title": "International Students Support Contest 2022-2023",
        "url": "https://kidatschool.com/international-students-support-contest-2022-2023",
        "grant": "Up to $2,000 awards",
        "description": "Applications are now open for a scholarships contest programme for 2022-2023! Our goal is to provide financial assistance and mentorship to students without the necessary means who demonstrate alignment with our values and want to further their education for the 2022-2023 academic year.\n\nDo you have a special academic interest, or are you passionate about something on a practical or theoretical level? Would you like financial support for your research project, dissertation, project, or anything else? Then you should participate in our competition!",
        "deadline": "2023-06-30 00:00",
        "degrees": ["BACHELOR", "PHD"]
    },
    {
        "country": "United STATES",
        "title": "Human Resource (HR) - Free Course From Windsor University's MBA",
        "url": "https://windsoruniversity.us/academics/master-of-business-administration-mba/mba-curriculum/phri/",
        "grant": "100% free",
        "description": "In this course, you will learn how to use integrated coaching, organizational development, career planning, and counseling skills to design, manage, and evaluate plans that improve the individual’s productivity, employability, and job satisfaction, as well as organizational effectiveness, employment, management development, customer service, and quality management. This course includes psychology, structure behavior, adult education principles, activity counseling, ability testing and evaluation, program design, consulting practice, structure development, and applications to issues.",
        "deadline": "2022-05-14 00:00",
        "degrees": ["MASTER"]
    },
    {
        "country": "india",
        "title": "Women in Tech Scholarship: Up to 50% off your Tuition Fee on all of our Programmes",
        "url": "https://bit.ly/Women-in-Tech-Scholarship",
        "grant": "50% off tuition fees",
        "description": "We created the [Women in Tech Scholarship](https://bit.ly/Women-in-Tech-Scholarship), to do our part to build a world that is diverse, equitable, and inclusive.\n\nApply by March 31, 2022, to receive up to 50% off your tuition fee and a reduced application fee.\n\nLast year we awarded €412,000 to 25 students.\n\nOur programmes:\n\n* Data Science\n* Computer Science\n* Front-end Development\n* Cyber Security\n* Product Management\n* Fintech\n* Interaction Design\n* Digital Marketing\n* High-tech Entrepreneurship",
        "deadline": "2022-03-31 00:00",
        "degrees": ["MASTER", "PHD"]
    },
    {
        "country": "india",
        "title": "Study Product Management with a 50% Scholarship",
        "url": "https://scholarships.harbour.space/product-management?utm_source=studyportals&utm_medium=listing&utm_campaign=both_b2c_pm-launch",
        "grant": "Get up to 11,450€ off your tuition fee",
        "description": "We’re searching for talented individuals to join the first batch of our Product Management programme.\n\n  \n\nIf you would like to kick-start your product career, don’t miss this opportunity!",
        "deadline": "2022-03-31 00:00",
        "degrees": ["BACHELOR"]
    }
]
```

### POST - Search Scholarships (No Auth token required)
Request:
```text
{{server}}/api/v1/scholarship/get
```
Payload:
```json
{
    "degrees": ["MASTER"],
    "countries": ["United states"],
    "search": null,
    "page": 0
}
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "scholarships": [
            {
                "title": "Human Resource (HR) - Free Course From Windsor University's MBA",
                "url": "https://windsoruniversity.us/academics/master-of-business-administration-mba/mba-curriculum/phri/",
                "grant": "100% free",
                "description": "In this course, you will learn how to use integrated coaching, organizational development, career planning, and counseling skills to design, manage, and evaluate plans that improve the individual’s productivity, employability, and job satisfaction, as well as organizational effectiveness, employment, management development, customer service, and quality management. This course includes psychology, structure behavior, adult education principles, activity counseling, ability testing and evaluation, program design, consulting practice, structure development, and applications to issues.",
                "deadline": "2022-05-14 00:00",
                "country": "united states",
                "degrees": [
                    "MASTER"
                ],
                "createdAt": "2022-08-14T04:02:19.624"
            }
        ]
    },
    "error": null
}
```

### GET - Get all countries (No Auth token required)
Request:
```text
{{server}}/api/v1/scholarship/get/countries
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "countries": [
            "india",
            "united states"
        ]
    },
    "error": null
}
```

## Sample User data
```json
[
    {
        "firstName": "Josephine",
        "lastName": "Page",
        "username": "test_1",
        "password": "123456",
        "email": "ipsum.ac@icloud.org",
        "country": null,
        "degree": null,
        "roles": [
            "ADMIN",
            "USER"
        ]
    },
    {
        "firstName": "Hayes",
        "lastName": "Contreras",
        "username": "test_2",
        "password": "123456",
        "email": "diam.lorem.auctor@aol.net",
        "country": null,
        "degree": null,
        "roles": [
            "ADMIN",
            "USER"
        ]
    },
    {
        "firstName": "Xanthus",
        "lastName": "Mejia",
        "username": "test_3",
        "password": "123456",
        "email": "egestas@icloud.ca",
        "country": null,
        "degree": null,
        "roles": [
            "ADMIN",
            "USER"
        ]
    },
    {
        "firstName": "Ashton",
        "lastName": "Travis",
        "username": "test_4",
        "password": "123456",
        "email": "porttitor.eros.nec@icloud.ca",
        "country": "india",
        "degree": "BACHELOR",
        "roles": [
            "USER"
        ]
    },
    {
        "firstName": "Miriam",
        "lastName": "Rocha",
        "username": "test_5",
        "password": "123456",
        "email": "faucibus.morbi@hotmail.com",
        "country": "united states",
        "degree": "MASTER",
        "roles": [
            "USER"
        ]
    }
]
```

## Sample Scholarship data
```json
[
    {
        "country": "INDIA",
        "title": "International Students Support Contest 2022-2023",
        "url": "https://kidatschool.com/international-students-support-contest-2022-2023",
        "grant": "Up to $2,000 awards",
        "description": "Applications are now open for a scholarships contest programme for 2022-2023! Our goal is to provide financial assistance and mentorship to students without the necessary means who demonstrate alignment with our values and want to further their education for the 2022-2023 academic year.\n\nDo you have a special academic interest, or are you passionate about something on a practical or theoretical level? Would you like financial support for your research project, dissertation, project, or anything else? Then you should participate in our competition!",
        "deadline": "2023-06-30 00:00",
        "degrees": ["BACHELOR", "PHD"]
    },
    {
        "country": "United STATES",
        "title": "Human Resource (HR) - Free Course From Windsor University's MBA",
        "url": "https://windsoruniversity.us/academics/master-of-business-administration-mba/mba-curriculum/phri/",
        "grant": "100% free",
        "description": "In this course, you will learn how to use integrated coaching, organizational development, career planning, and counseling skills to design, manage, and evaluate plans that improve the individual’s productivity, employability, and job satisfaction, as well as organizational effectiveness, employment, management development, customer service, and quality management. This course includes psychology, structure behavior, adult education principles, activity counseling, ability testing and evaluation, program design, consulting practice, structure development, and applications to issues.",
        "deadline": "2022-05-14 00:00",
        "degrees": ["MASTER"]
    },
    {
        "country": "india",
        "title": "Women in Tech Scholarship: Up to 50% off your Tuition Fee on all of our Programmes",
        "url": "https://bit.ly/Women-in-Tech-Scholarship",
        "grant": "50% off tuition fees",
        "description": "We created the [Women in Tech Scholarship](https://bit.ly/Women-in-Tech-Scholarship), to do our part to build a world that is diverse, equitable, and inclusive.\n\nApply by March 31, 2022, to receive up to 50% off your tuition fee and a reduced application fee.\n\nLast year we awarded €412,000 to 25 students.\n\nOur programmes:\n\n* Data Science\n* Computer Science\n* Front-end Development\n* Cyber Security\n* Product Management\n* Fintech\n* Interaction Design\n* Digital Marketing\n* High-tech Entrepreneurship",
        "deadline": "2022-03-31 00:00",
        "degrees": ["MASTER", "PHD"]
    },
    {
        "country": "india",
        "title": "Study Product Management with a 50% Scholarship",
        "url": "https://scholarships.harbour.space/product-management?utm_source=studyportals&utm_medium=listing&utm_campaign=both_b2c_pm-launch",
        "grant": "Get up to 11,450€ off your tuition fee",
        "description": "We’re searching for talented individuals to join the first batch of our Product Management programme.\n\n  \n\nIf you would like to kick-start your product career, don’t miss this opportunity!",
        "deadline": "2022-03-31 00:00",
        "degrees": ["BACHELOR"]
    }
]
```