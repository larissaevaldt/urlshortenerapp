# urlshortenerapp

<p>
  <img alt="PRs welcome!" src="https://img.shields.io/static/v1?label=PRs&message=WELCOME&style=for-the-badge&color=4A90E2&labelColor=222222" />
     
  <img alt="GitHub license" src="https://img.shields.io/github/license/larissaevaldt/urlshortenerapp?color=4A90E2&label=LICENSE&logo=3C424B&logoColor=3C424B&style=for-the-badge&labelColor=222222" />

  <a href="https://github.com/larissaevaldt">
    <img alt="Follow larissaevaldt" src="https://img.shields.io/static/v1?label=Follow&message=larissaevaldt&style=for-the-badge&color=4A90E2&labelColor=222222" />
  </a>
</p>


## About
#### 💡 This project is an API for short URL creation

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Postgres](https://www.postgresql.org/)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)

## Installation
#### With Docker installed, run the following commands:
```
$ git clone https://github.com/larissaevaldt/urlshortenerapp.git
$ cd urlshortenerapp
$ docker-compose up
```

## Usage

Request Method | URI | Body (JSON) | Description |  
:---: | :--- | :---: | :--- |
GET | http://localhost/api/v1/{code} | - | Retrieve original URL and redirect user to the correct Website | 
POST | http://localhost/api/v1 | { "longUrl": "https://siteyouwant" } | Create a unique short URL and return it in the response body |

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request


<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.


<!-- CONTACT -->
## Contact

Larissa Evaldt - https://www.linkedin.com/in/larissaevaldt/ - lari.justo@gmail.com

Project Link: [https://github.com/larissaevaldt/urlshortenerapp](https://github.com/larissaevaldt/urlshortenerapp)
