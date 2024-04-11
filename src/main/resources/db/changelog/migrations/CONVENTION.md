# Migration scripts
Scripts used for DB changes using flyway

###  Naming convention
```
V{DATE}.{JIRA TICKET NUMBER}.{migration_iteration_number}__{descriptive_title}.sql
```

###  Date format
```
yyyymmdd
```

###  Example
**Input Data:**

    * Date: 2020-02-06
    * Jira task: SE-1791
    
**Result:**
```
V20200206.1791.01__this_is_an_example.sql
```
