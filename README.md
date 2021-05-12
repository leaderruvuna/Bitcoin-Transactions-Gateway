

## BTC TX  GATEWAY API DOCS

### About the BTC GATEWAY

This BTC Gateway protocol is an API in charge of creating btc transactions between 2 wallet owners, 
input(sender) and output(receiver) wallets , sign and broadcast it to the Blockchain secure  network.

It is written in Spring boot for security and trustworthy reasons as spring boot is known as the hub of security when 
it comes to server side development.


### VISION

Our vision is to support crypto transactions for a quite number of cryptocurrencies including ethereum,litecoin,etc...


### API ENDPOINTS(supported)

* Create btc transaction: ```BASE_URL/api/gateway/btc/tx/create```

* Sign && broadcast btc transaction : ```BASE_URL/api/gateway/btc/tx/sign```

### REQUEST BODY(supported)

* Create btc __transaction__ **body**: ```{
      inputAddress:'',
      outputAddress:'',
      amount:10000,
  }```

* Sign btc __transaction__ **body**:``` {
      toSign:'',
  }```

### RESPONSE STATUS CODES

* Successful request: 200
* Forbidden data: 403
* Unauthorized request: 401
* Bad request: 400

### APPLICATION FOLDER STRUCTURE
```
> constants
    . StatusCodes.java
> controllers
    . BtcTransaction.java
> modals
    . TransactionsModal.java
> services
    . BlockCypherService.java
> utils
    > encryption
        . HashData.java
    > validators
        . BtcTransactionInputsValidator.java
```

### ARTICLE

The article is coming soon !


