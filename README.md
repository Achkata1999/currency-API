
# Currency API Service

This project is designed to handle currency data efficiently by integrating with external APIs, managing relational databases, providing REST and XML APIs, and utilizing RabbitMQ for request processing. Additionally, Redis caching is implemented to optimize database communication.


## Features

### 1. Currency Data Collection
- Fetches real-time currency data from Fixer.io.
- Stores the data in a relational database.
- Updates currency data at predefined intervals (configured in the application).

### 2. REST API Endpoints
JSON API:

- Supports two POST endpoints:
  
#### /json_api/current

```javascript
{
    "requestId": "b89577fe-8c37-4962-8af3-7cb89a245160",
    "timestamp": 1586335186721,
    "client": "1234",
    "currency": "EUR"
}
```
- Checks for duplicate requests by requestId.
- Returns the latest available data for the specified currency.

#### /json_api/history

```javascript
{
    "requestId": "b89577fe-8c37-4962-8af3-7cb89a24q909",
    "timestamp": 1586335186721,
    "client": "1234",
    "currency": "EUR",
    "period": 24
}
```
- Checks for duplicate requests by requestId.
- Returns historical data for the specified currency over the last period hours.

XML API:
- Provides a single POST endpoint:

#### /xml_api/command
- Current data request:

```javascript
<command id="1234">
    <get consumer="13617162">
        <currency>EUR</currency>
    </get>
</command>
```

- Historical data request::

```javascript
<command id="1234-8785">
    <history consumer="13617162" currency="EUR" period="24"/>
</command>
```

- id attribute in <command> uniquely identifies the request.

- consumer attribute represents the client ID.

- Duplicate request checks are implemented.

### 3. Unified Statistics Collection

Collects metadata for all incoming requests from REST and XML APIs:

- Service name/ID (EXT_SERVICE_1, EXT_SERVICE_2)

- Request ID

- Timestamp (UTC)

- End client ID

- Stores the metadata in a relational database.

### 4. RabbitMQ Integration

- Forwards the unified request metadata to a predefined RabbitMQ exchange.

- The exchange name is configurable in the application properties.

### 5. Redis Caching

- Implements Redis caching for database interactions to improve performance.

