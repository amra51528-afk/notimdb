# NotIMDb
<hr>
This app is based on Springboot application with gradle.

For this Java course we wanted to create something different, so we created this MovieApi called NotIMDb. More correctly an app from witch could dbe taken information about movies and their data such as director, actors, genres, reviews.
<ul>
<li>Movies - Where we register all movies.
Data:(Title, Date, Description, Rating, Actors, Director, Genres, reviews)</li>
<li>
We can CRUD on all entities.
</li>
<li>
On movies we can sort by director,actors,genre.
</li>
<li>
Search movie by title.
</li>
<li>Create reviews from user.</li>
</ul>
<h4>Getting Started</h4>
<hr>



### Setup

<h4>Requirements</h4>
<h4>Install these software first:</h4>
<ul>
<li>InteliJ IDEA.</li>
<li>Java SE Development Kit.</li>
<li>Postman (For testing apis)</li>
<li>XAMPP (Apache server - that contains MariaDB, PHP)</li>
</ul>

<hr>
<h3>Programming Languages used</h3>

![Java](https://img.shields.io/badge/Language-Java-red)
![SQL](https://img.shields.io/badge/Language-SQL-red)

<hr>

### Installations

Once you clone this project from GitHub or download it in zip, then you need to unzip and open it, in your editor IntelliJ IDEA (make sure
you click on TrustProject also after that let IntelliJ IDEA to install dependencies that we need
to open and run.
<hr>


### Technologies and APIs
<p>Postman is an API platform for building and using APIs. Postman simplifies each step of the API lifecycle and streamlines collaboration so you can create better APIs—faster.</p>  <br>  
<p>XAMPP is an abbreviation for cross-platform, and it allows you to build projects with API's offline, on a local web server on your computer. </p>
<hr>

## Starting the aplication

1.First thing you have to do for running the project is to turn on APACHE SERVER AND MYSQL on XAMPP, then they will take ports that they need.

2.javaproject.sql => this is the file of database, this one you need to import on your database. ('localhost/phpmyadmin').

3.Also you need to check 'application.properties', for example ...

```properties
server.port=8081

spring.datasource.url=jdbc:mysql://localhost:3307/moviedb
spring.datasource.username=root

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

### Dependencies

```java
plugins {
  id 'java'
  id 'org.springframework.boot' version '3.2.1'
  id 'io.spring.dependency-management' version '1.1.4'
}

group = 'com.notimdb'
version = '0.0.1-SNAPSHOT'

java {
  sourceCompatibility = '17'
}

repositories {
  mavenCentral()
}

dependencies {
  implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
  implementation 'org.springframework.boot:spring-boot-starter-web'
  runtimeOnly 'com.mysql:mysql-connector-j'
  testImplementation 'org.springframework.boot:spring-boot-starter-test'
}

tasks.named('test') {
  useJUnitPlatform()
}

```

<hr>

This Endpoint will help as get the list of all genres that are saved on our database
```java
 @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return (List<Genre>) genreRepository.findAll();
    }
```

TESTING API => getAllGenres();
```json
[
	{
		"id": 1,
		"name": "Action"
	},
	{
		"id": 2,
		"name": "Comedy"
	},
	{
		"id": 3,
		"name": "Drama"
	},
	{
		"id": 4,
		"name": "Sci-Fi"
	}
]
```
<hr>
This is the endpoint that we use to create a new genre and register it in our database

```java
 @PostMapping("/genres")
    public Genre createGenre(@RequestBody GenreCreateRequest newGenre) {
        Genre genre = new Genre();
        genre.setName(newGenre.getName());
        return genreRepository.save(genre);
    }
```

<hr>
These are 2 more endpoints that we use: to find all genres and to find a genre by Id

```java
 @GetMapping("actors/count")
public Long getTotalNrOfActors(){
        return actorRepository.count();
        }

    @GetMapping("/genres/{id}")
    public Genre getGenreById(@PathVariable Integer id){
        return genreRepository.findById(id).orElse(null);
    }
```

<hr>
This Endpoint will help as get the list of all actores that are stored and registered on our database.

```java
@GetMapping("/actors")
        public List<Actor> getAllActors() {
            return (List<Actor>) actorRepository.findAll();
        }
```

Testing API => GetAllActors();

```json
[
	{
		"id": 1,
		"firstName": "Tim",
		"lastName": "Robbins",
		"birthDate": "1972-02-03",
		"nationality": "English"
	},
	{
		"id": 2,
		"firstName": "Morgan",
		"lastName": "Freeman",
		"birthDate": "1975-01-03",
		"nationality": "English"
	},
	{
		"id": 3,
		"firstName": "Bob",
		"lastName": "Guntot",
		"birthDate": "1982-01-03",
		"nationality": "English"
	}
```
<hr>
This is the endpoint that we use to create new actors and save them in the database

```java
  @PostMapping("/actors")
        public Actor createActors(@RequestBody ActorCreateRequest newActor) {
            Actor actor = new Actor();
            actor.setFirstName(newActor.getFirstName());
            actor.setLastName(newActor.getLastName());
            actor.setBirthDate(newActor.getBirthDate());
            actor.setNationality(newActor.getNationality());
            return actorRepository.save(actor);
        }
```

<hr>

And also the last 2 Actors Endpoints that we use to get actor by Id and get total number of actors

```java
 @GetMapping("actors/count")
    public Long getTotalNrOfActors(){
        return actorRepository.count();
    }

 @GetMapping("/actors/{id}")
        public Actor getActorById(@PathVariable Integer id){
            return actorRepository.findById(id).orElse(null);
        }
```

<hr>

Now we continue with the directors. This is the endpoint that we use to create a new director and store it on our database

```java
@PostMapping("/directors")
    public Director createDirector(@RequestBody DirectorCreateRequest newDirector) {
        Director director = new Director();
        director.setFirstName(newDirector.getFirstName());
       director.setLastName(newDirector.getLastName());
       director.setBirthDate(newDirector.getBirthDate());
       director.setNationality(newDirector.getNationality());

       return directorRepository.save(director);
    }
```

Testing API => GetAllDirectors();

```json
[
	{
		"id": 1,
		"firstName": "Frank",
		"lastName": "Darabont",
		"birthDate": "1972-02-03",
		"nationality": "Italian"
	},
	{
		"id": 2,
		"firstName": "Francis",
		"lastName": "Ford Koppola",
		"birthDate": "1942-02-03",
		"nationality": "Italian"
	},
	{
		"id": 3,
		"firstName": "Christopher",
		"lastName": "Nolan",
		"birthDate": "1972-02-03",
		"nationality": "American"
	}
```

<hr>
Here are the endpoints that we use to find all directors, find director by Id and get total nr of directors:

```java
  @GetMapping("/directors")
    public List<Director> getAllDirectors() {
        return (List<Director>) directorRepository.findAll();
    }
    @GetMapping("/directors/{id}")
    public Director getDirectorById(@PathVariable Integer id){
        return directorRepository.findById(id).orElse(null);
    }
    @GetMapping("directors/count")
    public Long getTotalNrOfDirectors(){
        return directorRepository.count();
    }
```

<hr>

Now we continue with the users.
These are the endpoints that we send to: create a new user, get all users, get user by Id and delete user:

```java
 @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest newUserRequest) {
        User user = new User();
        user.setUsername(newUserRequest.getUsername());
        user.setPassword(newUserRequest.getPassword()); // Note: In a real-world application, use secure password hashing

        return userService.addUser(user);
    }

   @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

  @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

  @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
```
<hr>
This is the endpoint that we use to add a review to a movie from an user. It contains the relationships: user-review and review-movie

```java
 @PostMapping("/users/movie/{id}")
    public Movie addMovieReview(@PathVariable Integer id, @RequestBody CreateReviewFromUser newReview) {

        return userService.addMovieReview(id,newReview);
    }
```

Testing API => addMovieReview();
```json
{
	"rating":7.7,
	"comment":"Nice movie",
	"userId":2
}
```
Result:
```json
{
	"id": 1,
	"title": "The Shawshank Redemption",
	"releaseDate": "1994-03-22",
	"description": "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion",
	"rating": 7.8,
	"posterUrl": "poster.jpg",
	"director": {
		///
	},
	"genres": [
      ///
	],
	"actors": [
		///
	],
	"reviews": [
		{
			"id": 10,
			"rating": 7.7,
			"comment": "Nice movie"
		}
	]
}
```

<hr>
And here are the endpoints we use to get All reviews and get Review by Id

```java
 @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return (List<Review>) reviewRepository.findAll();
    }
    @GetMapping("/reviews/{id}")
    public Review getReviewById(@PathVariable Integer id){
        return reviewRepository.findById(id).orElse(null);
    }
```

Testing API => GetAllReviews();
```json
[
	{
		"id": 1,
		"rating": 9.0,
		"comment": "Best movie I've ever seen"
	},
	{
		"id": 2,
		"rating": 8.0,
		"comment": "Very funny movie"
	}
]
```
<hr>
Now we continue with the most important part, the movies:

Here are the enpoints that we use to: getAllMovies, getMovieById, deleteMovie, getTotalNrOfMovies, and update Movie

```java
 @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }
    
    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable int id){
        return movieService.getMovieById(id);
    }
  
   @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable Integer id) {
        movieService.deleteMovie(id);
    }

    @GetMapping("movies/count")
    public Long getTotalNrOfMovies(){
        return movieService.getTotalNrOfMovies();
    }

@PutMapping("/movies/{id}")
public Movie update(
@PathVariable Integer id,
@RequestBody MovieUpdateRequest request) {

        return movieService.updateMovie(id,request);
        }
```
<hr>
Below, you can find the endpoints: GetMovieByDirector, GetMovieByActor, GetMovieByGenre. In these endpoints are involved the relationships: movie-director, movie-actor, movie-genre

```java
  @GetMapping("movies/actor/{id}")
public List<Movie> getMovieByActor(@PathVariable Integer id){
        return movieService.getMovieByActor(id);
        }

@GetMapping("movies/genre/{id}")
public List<Movie> getMoviesByGenre(@PathVariable Integer id){
        return movieService.getMovieByGenre(id);
        }

@GetMapping("movies/director/{id}")
public List<Movie> getMoviesByDirector(@PathVariable Integer id){
        return movieService.getMovieByDirector(id);
        }
```

<hr>
This is the endpoint getMoviesByTitle: you can type the title and it will show up the movie

```java
 @GetMapping(value = {"/movies/search","movies/search/{title}"})
    public List<Movie> getMoviesByTitle(@PathVariable(required = false) String title){
        return movieService.searchMovieByTitle(title);
    }
```

Testing API => getMoviesByTitle
http://localhost:8081/movies/search/Hangover
```json
[
	{
		"id": 5,
		"title": "The Hangover",
		"releaseDate": "2009-03-22",
		"description": "Three buddies wake up from a bachelor party in Las Vegas, with no memory of the previous night and the bachelor missing. They make their way around the city in order to find their friend before his wedding.",
		"rating": 8.5,
		"posterUrl": "poster.jpg",
		"director": {
			"id": 5,
			"firstName": "Todd",
			"lastName": "Philips",
			"birthDate": "1971-02-03",
			"nationality": "French"
		},
		"genres": [
			{
				///
			}
		],
		"actors": [
			///
		],
		"reviews": [
				///
		]
	}
]
```

<hr>
This is the endpoint to getTopRatedMovies, with or without Genre, it works with both

```java
  @GetMapping(value = {"/movies/toprated","movies/toprated/{id}"})
    public List<Movie> getTopRated(@PathVariable(required = false) Integer id){
        return movieService.TopTenMovies(id);
    }
```

Testing API => GetTopRatedMovies by Genre: Drama
http://localhost:8081/movies/toprated/3

```json
[
	{
		"id": 2,
		"title": "The Monster",
		"releaseDate": "1972-03-22",
		"description": "Don Vito Corleone, head of a mafia family, decides to hand over his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.",
		"rating": 8.3,
		"posterUrl": "poster.jpg",
		"director": {
			"id": 2,
			"firstName": "Francis",
			"lastName": "Ford Koppola",
			"birthDate": "1942-02-03",
			"nationality": "Italian"
		},
		"genres": [
			{
				"id": 3,
				"name": "Drama"
			},
			{
				"id": 11,
				"name": "Crime"
			}
		],
		"actors": [
			{
				"id": 4,
				"firstName": "Marlon",
				"lastName": "Brando",
				"birthDate": "1943-01-03",
				"nationality": "Italian"
			},
			{
				"id": 6,
				"firstName": "James",
				"lastName": "Caan",
				"birthDate": "1932-01-03",
				"nationality": "Italian"
			},
			{
				"id": 5,
				"firstName": "Al",
				"lastName": "Pacino",
				"birthDate": "1943-01-03",
				"nationality": "Italian"
			}
		],
		"reviews": [
			{
				"id": 7,
				"rating": 8.5,
				"comment": "I suggest this movie to everyone"
			},
			{
				"id": 8,
				"rating": 8.1,
				"comment": "Enjoyed this movie"
			}
		]
	},
	{
		"id": 1,
		"title": "The Shawshank Redemption",
		"releaseDate": "1994-03-22",
		"description": "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion",
		"rating": 7.8,
		"posterUrl": "poster.jpg",
		"director": {
			"id": 1,
			"firstName": "Frank",
			"lastName": "Darabont",
			"birthDate": "1972-02-03",
			"nationality": "Italian"
		},
		"genres": [
			{
				"id": 3,
				"name": "Drama"
			}
		],
		"actors": [
			{
				"id": 1,
				"firstName": "Tim",
				"lastName": "Robbins",
				"birthDate": "1972-02-03",
				"nationality": "English"
			},
			{
				"id": 2,
				"firstName": "Morgan",
				"lastName": "Freeman",
				"birthDate": "1975-01-03",
				"nationality": "English"
			},
			{
				"id": 3,
				"firstName": "Bob",
				"lastName": "Guntot",
				"birthDate": "1982-01-03",
				"nationality": "English"
			}
		],
		"reviews": [
			{
				"id": 10,
				"rating": 7.7,
				"comment": "Nice movie"
			},
			{
				"id": 9,
				"rating": 7.9,
				"comment": "Good movie"
			}
		]
	}
]
```
<hr>

### Bugs and Features Requests

aa31711@seeu.edu.mk

eh31893@seeu.edu.mk
