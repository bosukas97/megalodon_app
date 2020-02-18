#INFO
H2 database can be accessed at: http://localhost:8080/h2-console/
  Just hit connect and you will see a user you created (click table name on left and the select statement should show up)
H2 databases are just for testing- but they make it very easy to switch out for a real sql database.

application endpoints of interest are: http://localhost:8080/users
                                       http://localhost:8080/login


POST to  http://localhost:8080/users  
```{
	"username": "bobbyj",
	"email": "bob@bob.comj",
	"password": "123234fg465"
}
```

Login with POST to localhost:8080/login

```{
	"email":"bob@bob.comj",
	"password": "123234fg465"
}
```

JWT is returned in the response header (This will be used to validate use of all other endpoints)
