# TripGenius

> **TripGenius** help travelers plan their holidays more efficiently while also providing personalized recommendations based on their preferences, budget, and alternative transportation option.

- [API PLANNING](https://neunicorn.github.io/tripgenius-api-doc/)
- [API DOCUMENTATION](https://documenter.getpostman.com/view/18445046/2s93sZ5DKM)

## Getting Started

1. Prequerities :

- **NodeJs** : `v18.16.0`
- **NPM** : `9.5.1`
- **Docker** : `20.10.23`

2. Initial Setup :

   - Clone the project from our [repository](https://github.com/neunicorn/TripGenius-CC)
   - Install the dependencies by following the command bellow :
     ```
     npm install
     ```
   - Fill in the env variable of the .env.example file and rename the file to .env
   - Start the development mode by following command bellow :

     ```
     npm run start-dev
     ```

     By default the application will run on port `8080`. To change this, you can change on the .env `PORT` or you can hardcode on the `src/app.js` file on the listen arguments.

3. Building Container Image :

   - Build your image with command:

     ```
     docker build -t tripgenius/local:latest .
     ```

   - Run docker image
     ```
     docker run -p 8080:8080 tripgenius/local:latest
     ```

## Dependencies and Technologies

To create this project we used several dependencies and technologies, among others:

- **@google-cloud/storage** : `6.10.1`
- **bcrypt** : `5.1.0`
- **body-parser** : `1.20.2`
- **cors** : `2.8.5`
- **ExpressJs** : `4.18.2`
- **JsonWebtoken** : `9.0.0`
- **multer** : `1.4.5-lts.1`
- **mysql2** : `3.3.1`
