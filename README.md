1. Run `docker build -t demo .`

2. Run `docker run -p 8080:8080 demo`

cURL to demo the api


1. Create author 

curl --request POST \
  --url http://localhost:8080/authors \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9 \
  --data '{
	"name": "Adam",
	"birthday": "01-01-2000"
}'


curl --request POST \
  --url http://localhost:8080/authors \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9 \
  --data '{
	"name": "Bob",
	"birthday": "01-01-1995"
}'

2. Create books

curl --request POST \
  --url http://localhost:8080/books \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9 \
  --data '{
    "book": {
        "isbn": "978-0132350881",
        "title": "Cooking",
        "year": 2019,
        "price": 49.99,
        "genre": "Technology"
    },
    "authorIds": [1, 2]
}
'

curl --request POST \
  --url http://localhost:8080/books \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9 \
  --data '{
    "book": {
        "isbn": "978-0132350882",
        "title": "Exercise",
        "year": 2019,
        "price": 49.99,
        "genre": "Technology"
    },
    "authorIds": [1]
}
'

3. Get all books 

curl --request GET \
  --url http://localhost:8080/books \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9

4. Update book

curl --request PUT \
  --url http://localhost:8080/books/978-0132350882 \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9 \
  --data '{
    "book": {
        "title": "Python 123",
        "year": 2019,
        "price": 49.99,
        "genre": "Technology"
    },
    "authorIds": [1]
}
'

5. Login as admin to get jwt

curl --request POST \
  --url http://localhost:8080/auth/login \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9 \
  --data '{
	"username": "admin",
	"password": "admin"
}'

6. Delete a book

curl --request DELETE \
  --url http://localhost:8080/books/978-0132350882 \
  --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTc3Njk0MDI2MiwiZXhwIjoxNzc3MDI2NjYyfQ.eBbDBcUXW8Mqsv4slOoWZz3dXZMWhSWhSOuA2JY_HEU' \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/12.5.0' \
  --cookie JSESSIONID=F21FA8231EA13E8D77776515F445A6F9