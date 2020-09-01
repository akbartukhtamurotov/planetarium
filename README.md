# Planetarium

Add planets to your database, update and retrive.

## Installation

**Create MySQL database with the name `planetarium`**

Log in to your database with your user (root or another)

```bash
mysql -u root -p;
```
Type your password, this will open MySQL console. Create there database with the name `planetarium`:

```bash
create database planetarium;
```

And set your MySQL password and user in `application.properties` file, if you choose different database name, then database name too.

## Usage

**Change directory to project root folder and build project:**

```bash
cd planetarium/
./gradlew build
```

**To run the application as a jar application, run the following command**

```bash
java -jar build/libs/planetarium-0.0.1-SNAPSHOT.jar
```

**To test the application in the project root folder run:**

```bash
./gradlew test -i
```

#### Endpoints:

Create planet:
`POST: /planets`
Request body, `application/json`
```json
{
    "name": "Mars",
    "gravity": 3.72076,
    "satellites": 2,
    "maxTemperature": 200,
    "minTemperature": 300
}
```

Search planet by `id`:
`GET: /planets/{id}`

Update planet:
`PUT: /planets/{id}`
Request body, `application/json`
```json
{
    "name": "Mars",
    "gravity": 3.72076,
    "satellites": 2,
    "maxTemperature": 200,
    "minTemperature": 300
}
```

## License
[MIT](https://choosealicense.com/licenses/mit/)