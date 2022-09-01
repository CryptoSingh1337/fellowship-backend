# Fellowship Backend

![](https://img.shields.io/badge/Java-17-blue)
![](https://img.shields.io/badge/Spring%20Boot-2.6.10-70b34a)
![](https://img.shields.io/badge/Nuxt.js-2.15.8-2ddc87)
![](https://img.shields.io/badge/Vuetify.js-2.6.1-2e95f2)

A Scholarship Aggregator which updates in real time. It has the following functionality:

- Minimalistic UI
- Scrap scholarships from various sources.
- Admin dashboard where admin can update scholarships, user's data and can add scholarships from the portal itself using the JSON file or can directly paste the JSON string.
- Sends notifications to the registered user about the recent scholarship which are added on the portal.
- Auto remove all the expired scholarships.
- Recommend scholarships based on user details (country, degree, field of study, branch, title, family income and category).
- User can search scholarships based on country, degree, field of study, branch, title, family income and category.

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

```json
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
        "id": "62f8b5e15d614855d67407b2",
        "firstName": "Josephine",
        "lastName": "Page",
        "username": "test_1",
        "email": "ipsum.ac@icloud.org",
        "country": "afghanistan",
        "degree": "BACHELOR",
        "roles": [
          "ADMIN",
          "USER"
        ]
      },
      {
        "id": "62f8b5e15d614855d67407b3",
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
        "id": "62f8b5e15d614855d67407b4",
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
        "id": "62f8b5e15d614855d67407b5",
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
        "id": "62f8b5e15d614855d67407b6",
        "firstName": "Miriam",
        "lastName": "Rocha",
        "username": "test_5",
        "email": "faucibus.morbi@hotmail.com",
        "country": "united states",
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
    "scholarships": {
      "content": [
        {
          "title": "Chinese Government Scholarship – Excellence Scholarship for International Student Program",
          "url": "https://www.scholarshipportal.com/scholarship/chinese-government-scholarship-excellence-scholarship-for-international-student-program",
          "grant": "EUR 4300",
          "description": "Chinese Government Scholarship – Excellence Scholarship for International Student Program is available to current students, who are on their second year or above full-time bachelor’s/master’s/doctoral programmes.",
          "deadline": "2022-09-14 23:47:47.446453",
          "country": [
            "united states",
            "united kingdom",
            "canada",
            "south africa",
            "nigeria",
            "pakistan",
            "india"
          ],
          "programme": [
            "b.tech",
            "m.tech"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 1000000,
          "createdAt": "2022-08-25T12:43:05.552"
        },
        {
          "title": "Poland My First Choice Programme",
          "url": "https://www.scholarshipportal.com/scholarship/poland-my-first-choice-programme",
          "grant": "EUR approximately 460 per month ",
          "description": "The Poland My First Choice scholarship programme aims to encourage young talented people from the countries listed to pursue studies at the best Polish HEIs.",
          "deadline": "2022-09-14 23:47:47.446453",
          "country": [
            "united states",
            "united kingdom",
            "canada"
          ],
          "programme": [
            "b.tech",
            "m.tech"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 800000,
          "createdAt": "2022-08-25T12:43:05.549"
        },
        {
          "title": "Merit Cum Means Scholarship For Professional and Technical Courses CS",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/MoMA_MCM_2018-20.pdf",
          "grant": "Part fee waiver (50%)",
          "description": "Ministry of Minority Affairs",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.tech",
            "m.tech"
          ],
          "branch": [
            "cse",
            "it"
          ],
          "category": [
            "SC/ST",
            "OBC"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 250000,
          "createdAt": "2022-08-25T12:43:05.546"
        },
        {
          "title": "PG SCHOLARSHIP SCHEME FOR SC ST STUDENTS FOR PERSUING PROFESSIONAL COURSES",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/guidelines_pgsprof_gl1819.pdf",
          "grant": "Part fee waiver (50%)",
          "description": "University Grants Commission - MHRD",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "m.tech",
            "mba",
            "m.com"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech",
            "agri",
            "phy",
            "chem"
          ],
          "category": [
            "SC/ST"
          ],
          "degrees": [
            "MASTER"
          ],
          "income": 20000,
          "createdAt": "2022-08-25T12:43:05.543"
        },
        {
          "title": "PG SCHOLARSHIP FOR UNIVERSITY",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/GuidelinesUGCUniversityRankHolder1819.pdf",
          "grant": "Part fee waiver (50%)",
          "description": "University Grants Commission - MHRD",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.tech",
            "b.sc",
            "b.com",
            "m.tech",
            "m.sc",
            "m.com"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech",
            "agri",
            "phy",
            "chem"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 20000,
          "createdAt": "2022-08-25T12:42:18.398"
        },
        {
          "title": "CENTRALLY SPONSORED SCHEME OF POST MATRIC SCHOLARSHIP FOR SC STUDENTS-MEGHALAYA",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/meghalaya/Guidelines_3054.pdf",
          "grant": "250000",
          "description": "The scope of the Scheme is to increase the Gross Enrollment Ratio of SC category implemented by AICTE to provide encouragement and support to pursue education to poor wards.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "SC/ST"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 100000,
          "createdAt": "2022-08-25T12:42:18.395"
        },
        {
          "title": "AICTE SWANATH SCHOLARSHIP SCHEME ( TECHNICAL DEGREE)",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/AICTE/AICTE_3039_G.pdf",
          "grant": "200000",
          "description": "The Scheme is being implemented by AICTE to provide encouragement and support to orphans, wards of parents died due to Covid-19, wards of Armed Forces and Central Paramilitary Forces martyred in action (Shaheed) to pursue education.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 120000,
          "createdAt": "2022-08-25T12:42:18.391"
        },
        {
          "title": "SAKSHAM SCHOLARSHIP SCHEME FOR SPECIALLY ABLED STUDENT (TECHNICAL DEGREE)",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/AICTE/AICTE_2012_G.pdf",
          "grant": "320000",
          "description": "Saksham is a MHRD Scheme being implemented by AICTE aimed at providing encouragement and support to specially-abled children to pursue technical education.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 150000,
          "createdAt": "2022-08-25T12:31:43.079"
        },
        {
          "title": "PRAGATI SCHOLARSHIP SCHEME FOR GIRL STUDENTS ( TECHNICAL DEGREE)",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/AICTE/AICTE_2010_G.pdf",
          "grant": "120000",
          "description": "Scheme being implemented by AICTE aimed at providing assistance for advancement of Girls pursuing technical education.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "SC/ST"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 100000,
          "createdAt": "2022-08-25T12:31:43.077"
        },
        {
          "title": "ISHAN UDAY - Special Scholarship Scheme For North Eastern Region",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/ISHAN_UDAY_GUIDELINE.pdf",
          "grant": "300000",
          "description": "University Grants Commission - MHRD",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.tech",
            "b.sc",
            "b.com"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech",
            "agri",
            "phy",
            "chem"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR"
          ],
          "income": 190000,
          "createdAt": "2022-08-25T12:31:43.072"
        }
      ],
      "pageable": {
        "sort": {
          "empty": false,
          "sorted": true,
          "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
      },
      "last": false,
      "totalElements": 14,
      "totalPages": 2,
      "size": 10,
      "number": 0,
      "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
      },
      "first": true,
      "numberOfElements": 10,
      "empty": false
    }
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
    "title": "PG SCHOLARSHIP SCHEME FOR SC ST STUDENTS FOR PERSUING PROFESSIONAL COURSES",
    "degrees": [
      "MASTER"
    ],
    "programme": [
      "M.Tech",
      "MBA",
      "M.com"
    ],
    "income": null,
    "branch": [
      "CSE",
      "IT",
      "ECE",
      "Mech",
      "agri",
      "Phy",
      "Chem"
    ],
    "category": [
      "SC/ST"
    ],
    "deadline": "31-10-2022",
    "grant": "Part fee waiver (50%)",
    "country": [
      "India"
    ],
    "description": "University Grants Commission - MHRD",
    "url": "https://scholarships.gov.in/public/schemeGuidelines/guidelines_pgsprof_gl1819.pdf"
  },
  {
    "title": "Merit Cum Means Scholarship For Professional and Technical Courses CS",
    "degrees": [
      "BACHELOR",
      "MASTER"
    ],
    "programme": [
      "B.Tech",
      "M.Tech"
    ],
    "income": 250000,
    "branch": [
      "CSE",
      "IT"
    ],
    "category": [
      "SC/ST",
      "OBC"
    ],
    "deadline": "31-10-2022",
    "grant": "Part fee waiver (50%)",
    "country": [
      "India"
    ],
    "description": "Ministry of Minority Affairs",
    "url": "https://scholarships.gov.in/public/schemeGuidelines/MoMA_MCM_2018-20.pdf"
  },
  {
    "title": "Poland My First Choice Programme",
    "degrees": [
      "BACHELOR",
      "MASTER",
      "PHD"
    ],
    "programme": [
      "B.Tech",
      "M.Tech"
    ],
    "income": 800000,
    "branch": [
      "CSE",
      "IT",
      "ECE",
      "Mech"
    ],
    "category": [
      "Gen"
    ],
    "deadline": "2022-09-14 23:47:47.446453",
    "grant": "EUR approximately 460 per month ",
    "country": [
      "united states",
      "united kingdom",
      "canada"
    ],
    "description": "The Poland My First Choice scholarship programme aims to encourage young talented people from the countries listed to pursue studies at the best Polish HEIs.",
    "url": "https://www.scholarshipportal.com/scholarship/poland-my-first-choice-programme"
  },
  {
    "title": "Chinese Government Scholarship \u2013 Excellence Scholarship for International Student Program",
    "degrees": [
      "BACHELOR",
      "MASTER",
      "PHD"
    ],
    "programme": [
      "B.Tech",
      "M.Tech"
    ],
    "income": 1000000,
    "branch": [
      "CSE",
      "IT",
      "ECE",
      "Mech"
    ],
    "category": [
      "Gen"
    ],
    "deadline": "2022-09-14 23:47:47.446453",
    "grant": "EUR 4300",
    "country": [
      "united states",
      "united kingdom",
      "canada",
      "south africa",
      "nigeria",
      "pakistan",
      "india"
    ],
    "description": "Chinese Government Scholarship \u2013 Excellence Scholarship for International Student Program is available to current students, who are on their second year or above full-time bachelor\u2019s/master\u2019s/doctoral programmes.",
    "url": "https://www.scholarshipportal.com/scholarship/chinese-government-scholarship-excellence-scholarship-for-international-student-program"
  }
]
```

Response:
```json
{
    "responseStatus": "SUCCESS",
    "data": {
        "documentCreated": 4
    },
    "error": null
}
```

### POST - Search Scholarships (No Auth token required)

Request:

```text
{{server}}/api/v1/scholarship/get
```

Payload:

```json
{
  "degree": null,
  "countries": [],
  "programme": "",
  "branch": "",
  "category": [],
  "income": [
    20000,
    1500000
  ],
  "search": "",
  "page": 0
}
```

Response:

```json
{
  "responseStatus": "SUCCESS",
  "data": {
    "scholarships": {
      "content": [
        {
          "title": "Chinese Government Scholarship – Excellence Scholarship for International Student Program",
          "url": "https://www.scholarshipportal.com/scholarship/chinese-government-scholarship-excellence-scholarship-for-international-student-program",
          "grant": "EUR 4300",
          "description": "Chinese Government Scholarship – Excellence Scholarship for International Student Program is available to current students, who are on their second year or above full-time bachelor’s/master’s/doctoral programmes.",
          "deadline": "2022-09-14 23:47:47.446453",
          "country": [
            "united states",
            "united kingdom",
            "canada",
            "south africa",
            "nigeria",
            "pakistan",
            "india"
          ],
          "programme": [
            "b.tech",
            "m.tech"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 1000000,
          "createdAt": "2022-08-25T12:43:05.552"
        },
        {
          "title": "Poland My First Choice Programme",
          "url": "https://www.scholarshipportal.com/scholarship/poland-my-first-choice-programme",
          "grant": "EUR approximately 460 per month ",
          "description": "The Poland My First Choice scholarship programme aims to encourage young talented people from the countries listed to pursue studies at the best Polish HEIs.",
          "deadline": "2022-09-14 23:47:47.446453",
          "country": [
            "united states",
            "united kingdom",
            "canada"
          ],
          "programme": [
            "b.tech",
            "m.tech"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 800000,
          "createdAt": "2022-08-25T12:43:05.549"
        },
        {
          "title": "Merit Cum Means Scholarship For Professional and Technical Courses CS",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/MoMA_MCM_2018-20.pdf",
          "grant": "Part fee waiver (50%)",
          "description": "Ministry of Minority Affairs",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.tech",
            "m.tech"
          ],
          "branch": [
            "cse",
            "it"
          ],
          "category": [
            "SC/ST",
            "OBC"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 250000,
          "createdAt": "2022-08-25T12:43:05.546"
        },
        {
          "title": "PG SCHOLARSHIP SCHEME FOR SC ST STUDENTS FOR PERSUING PROFESSIONAL COURSES",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/guidelines_pgsprof_gl1819.pdf",
          "grant": "Part fee waiver (50%)",
          "description": "University Grants Commission - MHRD",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "m.tech",
            "mba",
            "m.com"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech",
            "agri",
            "phy",
            "chem"
          ],
          "category": [
            "SC/ST"
          ],
          "degrees": [
            "MASTER"
          ],
          "income": 20000,
          "createdAt": "2022-08-25T12:43:05.543"
        },
        {
          "title": "PG SCHOLARSHIP FOR UNIVERSITY",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/GuidelinesUGCUniversityRankHolder1819.pdf",
          "grant": "Part fee waiver (50%)",
          "description": "University Grants Commission - MHRD",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.tech",
            "b.sc",
            "b.com",
            "m.tech",
            "m.sc",
            "m.com"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech",
            "agri",
            "phy",
            "chem"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 20000,
          "createdAt": "2022-08-25T12:42:18.398"
        },
        {
          "title": "CENTRALLY SPONSORED SCHEME OF POST MATRIC SCHOLARSHIP FOR SC STUDENTS-MEGHALAYA",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/meghalaya/Guidelines_3054.pdf",
          "grant": "250000",
          "description": "The scope of the Scheme is to increase the Gross Enrollment Ratio of SC category implemented by AICTE to provide encouragement and support to pursue education to poor wards.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "SC/ST"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER",
            "PHD"
          ],
          "income": 100000,
          "createdAt": "2022-08-25T12:42:18.395"
        },
        {
          "title": "AICTE SWANATH SCHOLARSHIP SCHEME ( TECHNICAL DEGREE)",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/AICTE/AICTE_3039_G.pdf",
          "grant": "200000",
          "description": "The Scheme is being implemented by AICTE to provide encouragement and support to orphans, wards of parents died due to Covid-19, wards of Armed Forces and Central Paramilitary Forces martyred in action (Shaheed) to pursue education.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 120000,
          "createdAt": "2022-08-25T12:42:18.391"
        },
        {
          "title": "SAKSHAM SCHOLARSHIP SCHEME FOR SPECIALLY ABLED STUDENT (TECHNICAL DEGREE)",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/AICTE/AICTE_2012_G.pdf",
          "grant": "320000",
          "description": "Saksham is a MHRD Scheme being implemented by AICTE aimed at providing encouragement and support to specially-abled children to pursue technical education.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 150000,
          "createdAt": "2022-08-25T12:31:43.079"
        },
        {
          "title": "PRAGATI SCHOLARSHIP SCHEME FOR GIRL STUDENTS ( TECHNICAL DEGREE)",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/AICTE/AICTE_2010_G.pdf",
          "grant": "120000",
          "description": "Scheme being implemented by AICTE aimed at providing assistance for advancement of Girls pursuing technical education.",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "branch": [
            "b.sc",
            "phd",
            "b.com",
            "ba",
            "llb"
          ],
          "category": [
            "SC/ST"
          ],
          "degrees": [
            "BACHELOR",
            "MASTER"
          ],
          "income": 100000,
          "createdAt": "2022-08-25T12:31:43.077"
        },
        {
          "title": "ISHAN UDAY - Special Scholarship Scheme For North Eastern Region",
          "url": "https://scholarships.gov.in/public/schemeGuidelines/ISHAN_UDAY_GUIDELINE.pdf",
          "grant": "300000",
          "description": "University Grants Commission - MHRD",
          "deadline": "31-10-2022",
          "country": [
            "india"
          ],
          "programme": [
            "b.tech",
            "b.sc",
            "b.com"
          ],
          "branch": [
            "cse",
            "it",
            "ece",
            "mech",
            "agri",
            "phy",
            "chem"
          ],
          "category": [
            "GEN"
          ],
          "degrees": [
            "BACHELOR"
          ],
          "income": 190000,
          "createdAt": "2022-08-25T12:31:43.072"
        }
      ],
      "pageable": {
        "sort": {
          "empty": false,
          "sorted": true,
          "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 10,
        "paged": true,
        "unpaged": false
      },
      "last": false,
      "totalElements": 14,
      "totalPages": 2,
      "size": 10,
      "number": 0,
      "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
      },
      "first": true,
      "numberOfElements": 10,
      "empty": false
    }
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
    "country": [
      "INDIA",
      "United STATES"
    ],
    "title": "International Students Support Contest 2022-2023",
    "url": "https://kidatschool.com/international-students-support-contest-2022-2023",
    "grant": "Up to $2,000 awards",
    "description": "Applications are now open for a scholarships contest programme for 2022-2023! Our goal is to provide financial assistance and mentorship to students without the necessary means who demonstrate alignment with our values and want to further their education for the 2022-2023 academic year.\n\nDo you have a special academic interest, or are you passionate about something on a practical or theoretical level? Would you like financial support for your research project, dissertation, project, or anything else? Then you should participate in our competition!",
    "deadline": "2023-06-30 00:00",
    "degrees": [
      "BACHELOR",
      "PHD"
    ]
  },
  {
    "country": [
      "United STATES"
    ],
    "title": "Human Resource (HR) - Free Course From Windsor University's MBA",
    "url": "https://windsoruniversity.us/academics/master-of-business-administration-mba/mba-curriculum/phri/",
    "grant": "100% free",
    "description": "In this course, you will learn how to use integrated coaching, organizational development, career planning, and counseling skills to design, manage, and evaluate plans that improve the individual’s productivity, employability, and job satisfaction, as well as organizational effectiveness, employment, management development, customer service, and quality management. This course includes psychology, structure behavior, adult education principles, activity counseling, ability testing and evaluation, program design, consulting practice, structure development, and applications to issues.",
    "deadline": "2022-05-14 00:00",
    "degrees": [
      "MASTER"
    ]
  },
  {
    "country": [
      "india"
    ],
    "title": "Women in Tech Scholarship: Up to 50% off your Tuition Fee on all of our Programmes",
    "url": "https://bit.ly/Women-in-Tech-Scholarship",
    "grant": "50% off tuition fees",
    "description": "We created the [Women in Tech Scholarship](https://bit.ly/Women-in-Tech-Scholarship), to do our part to build a world that is diverse, equitable, and inclusive.\n\nApply by March 31, 2022, to receive up to 50% off your tuition fee and a reduced application fee.\n\nLast year we awarded €412,000 to 25 students.\n\nOur programmes:\n\n* Data Science\n* Computer Science\n* Front-end Development\n* Cyber Security\n* Product Management\n* Fintech\n* Interaction Design\n* Digital Marketing\n* High-tech Entrepreneurship",
    "deadline": "2022-03-31 00:00",
    "degrees": [
      "MASTER",
      "PHD"
    ]
  },
  {
    "country": [
      "india"
    ],
    "title": "Study Product Management with a 50% Scholarship",
    "url": "https://scholarships.harbour.space/product-management?utm_source=studyportals&utm_medium=listing&utm_campaign=both_b2c_pm-launch",
    "grant": "Get up to 11,450€ off your tuition fee",
    "description": "We’re searching for talented individuals to join the first batch of our Product Management programme.\n\n  \n\nIf you would like to kick-start your product career, don’t miss this opportunity!",
    "deadline": "2022-03-31 00:00",
    "degrees": [
      "BACHELOR"
    ]
  }
]
```
