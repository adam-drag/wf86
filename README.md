## How to run:

### By running jar file:

First install:
```bash
mvn clean install
```
Then run, this will run using default task.json data
```bash
java -jar target/wf86-1.0-SNAPSHOT.jar
```

if you want to run using custom.json run:
```bash
java -jar target/wf86-1.0-SNAPSHOT.jar "data/custom.json"
```



## Deliverables
### What does the JSON look like? Provide some examples
    In data folder are two files: task.json and custom.json
    task.json - this is JSON from given example
    custom.json - this one shows a bit more complex if-statment
### What does the design and data structure look like?
####  Design:
   Mapping of json into model class with some utility methods is in jsonmodel package
   Main logic of if evaluation is in evaluator package IfStatementEvaluator - it process mapped JSON instances from .json file
   
#### Data structure of json:
   On the top level I defined:
   input - this is a key-value map of variables used in the if-statements, like a='abc'
   statement - here is an if statement which contains a condition, the condition can be Comparison or Logical:
   - ComparisonCondition: this is a basic condition where variable is compared to input based on the operator
   - Logical condition: this allows to make 'AND' and 'OR' conditions between ComparisonConditions
### What are the APIs available?
   - org.example.evaluator.IfStatementEvaluator.evaluate to evaluate the json payload
   - org.example.translator.Translator - to translate json payload into more readable form, it hides the implementation
   so if a new format is needed we can add a new implementation.


## My comment
Initially I decided about json struct and implemented the algorithm in python - see `json_if.py` file -
it supports more complex conditions like:  `(a=='abc' && b >4) || c==0 || (e ==-1 && f == 1)` but when I was
rewriting it in java I had problem with parsing json using jackson. The root cause was the data struct in LogicalCondition:
it has a list of conditions where it could be another `logical` one or `comparison`. As this wasnt a direct requirement
and due to lack of time I gave up and java version supports more basic ones, so one if condition can have only ANDs or ORs.
I'm aware of it and normally I would spend some more time and fix it.