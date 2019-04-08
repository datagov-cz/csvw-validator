Csvw-validator
=======
Implementation of CSV on the Web validator.

## Requirements
Java - version 8

Maven - version 3.3.9+

## Installation
Run command:
```
mvn clean install
```
which will install the application.

## Appliaction start

### Web application

Start the web application with command:
```
mvn spring-boot:run
```

After success application startup, open ```http://localhost:8080/``` in our browser or use ```http://localhost:8080/validate```, ```http://localhost:8080/validateBatch``` endpoints.

### Command-line application
TODO
## Examples

### Validate endpoint

Example of using REST endpoint ```/validate```, which validates one tabular data file or metadata file or both together.
Request body has to be JSON with format:
```
{
	"tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
	"metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json",
	"strictMode": false
}
```
Where each property is optional, but at least one of "tabularUrl" or "metadataUrl" must be specified.
*TabularUrl* is string property for URL of tabular data file. *MetadataUrl* is string property for URL of metadata file.
*StrictMode* is boolean property, which disable/enable strict mode. By default is strict mode enabled.
In **strict mode** tabular files are validating against **RFC 4180** (CSV comma delimiter, line endings \r\n, UTF-8 encoding,...).

Response looks like this:
```
{
    "validationStatus": "ERROR",
    "tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
    "metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json",
    "validationErrors": [
        {
            "severity": "ERROR",
            "message": "Cell (row 2 column 1) cannot be formatted as 'integer' dataype."
        }
    ],
    "warningCount": 0,
    "errorCount": 1,
    "fatalCount": 0,
    "totalErrorsCount": 1
}
```
*ValidationStatus* is result of validation - PASSED, WARNING or ERROR.
*TabularUrl* and *metadataUrl* are URL of validated files.
*ValidationErrors* contains errors created during validation. Each error has severity (WARNING, ERROR, FATAL).
Then there are number of all errors (*totalErrorsCount*), warning errors (*warningCount*), error errorss (*errorCount*) and fatal errors (*fatalCount*).

Example request in cURL:
```
curl -X POST \
  http://localhost:8080/validate \
  -H 'Accept: application/json' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: cbfb628b-0444-48f4-8307-5f8a3d011644' \
  -H 'cache-control: no-cache' \
  -d '{
	"tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
	"metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json",
	"strictMode": false
}'
```
### ValidateBatch endpoint

Example of using REST endpoint ```/validateBatch```, which validates multiple tabular data/metadata files.
Request body has to be JSON with format:
```
{
    "filesToProcess": [
		{
			"tabularUrl": "http://www.w3.org/2013/csvw/tests/test282.csv",
			"metadataUrl": "http://www.w3.org/2013/csvw/tests/test282-metadata.json"
		},
		{
			"tabularUrl": "http://www.w3.org/2013/csvw/tests/test283.csv"
		},
		{
			"metadataUrl": "http://www.w3.org/2013/csvw/tests/test284-metadata.json"
		},
		{
			"tabularUrl": "http://www.w3.org/2013/csvw/tests/test286.csv",
			"metadataUrl": "http://www.w3.org/2013/csvw/tests/test286-metadata.json"
		}
	],
	"strictMode": false
}
```
Where *stricMode* is the same boolean property as in ```/validate``` endpoint. And property *filesToProcess* is array property, which contains object with *tabularUrl* and/or *metadataUrl* as in ```/validate``` endpoint.

Response has property *filesCount*, which contains number of validated files. Then there are counters for passed (*passedFileCount*), warning (*warningFileCount*) and error (*errorFileCount*) files. For each validated files, there is ValidationResponse (object from ```/validate``` endpoint) in *filesResults* property.

### Command-line application

## License
[MIT](https://choosealicense.com/licenses/mit/) 