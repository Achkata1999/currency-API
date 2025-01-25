
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

