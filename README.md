# Configure password for mongoDB url
1. Add a file called application-secrets.yml to path src/main/resources
2. Inside application-secrets.yml add mongodb, twilio and telegram secrets:
```
mongodb:
  password: YOUR_PASSWORD_HERE
twilio:
  AccountSID: ACCOUNT_SID
  AuthToken: AUTH_TOKEN
  phoneNumber: VIRTUAL_PHONE_NUMBER
telegram:
  authToken: BOT_AUTH_TOKEN
  username: BOT_USERNAME
```
### Telegram bot
To request otp use command : ```/requestotp```

To view the api documentation visit : http://localhost:8083/swagger-ui/index.html