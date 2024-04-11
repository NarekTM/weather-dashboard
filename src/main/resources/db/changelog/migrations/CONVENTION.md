# Migration scripts

Scripts used for DB changes using Liquibase

### Naming convention

```
V{DATE}.{TICKET NUMBER} OR {TASK DESCRIPTION}.{migration_iteration_number}__{descriptive_title}.sql
```

> ⚠️ The preferred one is the 'Ticket number' to include in the name!

### Date format

```
yyyymmdd
```

### Example

**Input Data:**

    * Date: 2020-02-06
    * Task number: SE-1234

**Result:**

```
V20200206.1234.01__this_is_the_descriptive_title_of_the_first_migration_file_of_the_task.sql
V20200206.1234.02__this_is_the_descriptive_title_of_the_second_migration_file_of_the_task.sql
```

**Input Data:**

    * Date: 2020-02-06
    * Task description: Add initial tables

**Result:**

```
V20200206.initial.01__add_initial_tables.sql
```
