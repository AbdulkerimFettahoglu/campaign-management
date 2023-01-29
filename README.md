# campaign-management

With this project customers can manage their own campaigns via using status of campaigns. 

### Docker

You can create your own image via using following commands. (Port numbers can change dependent to your docker run command.)

```
docker build --tag=campaign-management:latest .
docker run -p8080:8080 --name campaign-container campaign-management:latest
```

### Swagger UI

This project uses Swagger UI to list its webservices. To access swagger file, use following link:

```
http://localhost:8080/swagger-ui.html#/
```

### H2-Database

This project uses H2-Database as in-memory database solution. To access log-in page, use following link:

```
http://localhost:8080/h2-console/
Driver Class :org.h2.Driver
JDBC URL :jdbc:h2:mem:test
User Nam :sa
Password :
(No password)
```

## Summary

Application will automatically full-fill "category" and "status" tables. You don't need to do anything about them but you should know index values of "category".

```
      CATEGORY
---------------------
| Index |       Name      |
|   1   |        TSS      |
|   2   |        ÖSS      |
|   3   | Hayat Sigortası |
|   4   |       Diğer     |
---------------------
```

### Create Campaign
To create new campaign use following model:

```
(POST)http://localhost:8080/campaign
{
	"title": "string",
	"details": "string",
	"category": 0
}
```

For example (don't forget our validations):

```
{
	"title": "title12345",
	"details": "description_________",
	"category": 1
}
```

Other webservices:

```
*(GET)localhost:8080/campaign?campaignId=<id> :to get specific campaign from the database. (<id> should be replace with id number of the campaign)
*(PUT)localhost:8080/campaign/activate?campaignId=<id> :use this web service to activate campaign. (<id> should be replace with id number of the campaign)
*(PUT)localhost:8080/campaign/deactivate?campaignId=<id> :use this web service to de-activate campaign. (<id> should be replace with id number of the campaign)
*(GET)localhost:8080/campaign/history?campaignId=<id> :use this web service to see historical data of the campaign. (<id> should be replace with id number of the campaign)
*(GET)localhost:8080/dashboard/classifieds/statistics :it will give a summary about database.
```