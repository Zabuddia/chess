# ♕ BYU CS 240 Chess

This project demonstrates mastery of proper software design, client/server architecture, networking using HTTP and WebSocket, database persistence, unit testing, serialization, and security.

## 10k Architecture Overview

The application implements a multiplayer chess server and a command line chess client.

[![Sequence Diagram](10k-architecture.png)]([https://sequencediagram.org/index.html#initialData=C4S2BsFMAIGEAtIGckCh0AcCGAnUBjEbAO2DnBElIEZVs8RCSzYKrgAmO3AorU6AGVIOAG4jUAEyzAsAIyxIYAERnzFkdKgrFIuaKlaUa0ALQA+ISPE4AXNABWAexDFoAcywBbTcLEizS1VZBSVbbVc9HGgnADNYiN19QzZSDkCrfztHFzdPH1Q-Gwzg9TDEqJj4iuSjdmoMopF7LywAaxgvJ3FC6wCLaFLQyHCdSriEseSm6NMBurT7AFcMaWAYOSdcSRTjTka+7NaO6C6emZK1YdHI-Qma6N6ss3nU4Gpl1ZkNrZwdhfeByy9hwyBA7mIT2KAyGGhuSWi9wuc0sAI49nyMG6ElQQA](https://sequencediagram.org/index.html#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDAEooDmSAzmFMARDQVqhFHXyFiwUgBF+wAIIgQKLl0wATeQCNgXFDA3bMmdlAgBXbDADEaYFQCerDt178kg2wHcAFkjAxRFRSAFoAPnJKGigALhgAbQAFAHkyABUAXRgAegt9KAAdNABvfMp7AFsUABoYXDVvaA06lErgJAQAX0xhGJgIl04ePgEhaNF4qFceSgAKcqgq2vq9LiaoFpg2joQASkw2YfcxvtEByLkwRWVVLnj2FDAAVQKFguWDq5uVNQvDbTxFgAUQUMhgi2WMAAZuZKjBXpRvphvkpflwBoMjm5Rp5xiIVPE0BYEAhDjMTnizip-qjbmp4iBpvwUIioO8KsBqnUGutml95Gi7v8jPEAJIAOTIwJY6QhHy5K15Gy2O06MEl6RSCIKyOxIw8gmpBkGdPRjOZgQUFjAvg5S0VAuuQr+g1FGqlMrlkMVdWANt86QgAGt0B6tTBrbbkWbhaEsRTcUaJoSYP7bUHQ2hycck-iqOd45E+pR4unAyH0L1opRMZFgugwPEAEwABlbxRKMAARD7qt3Yt3lt26t3y5n0APu5As92YD10BoTGZLNYbNBpI8YAAZCCcNA+fyBTANsLFmtxRKpDLZHL6NR4zt9pVrFU9EuwBO5w35yYwBB7kgaD2ssPKvvyOY4j+xq0oK9L3BC2BaFaAYgY6KJweiETupK0qyvKnLcmmAYTgemralGvgxphcZflBpwpigZYkZW2b6pSyYEiagwfsxGasdW1C1kW8DII2Lbtp2PbPlOw6juOrFTjOk7zpgi7LuYVi2GYKBhru7CWMwNh+AEQRiWeURCZeCQyMC27AukwK3veXCPvYLFZoJ-QROxebGvEAEGTacwKVmBy+dBjERLGDIwI8YCUSFHnoE6Px3NhgKsKC4KhWGsIQPClHUc68F1kM9FUox8QAITEqSkEGgxXF1rxMB1WSH51qeTYwG2HZoO1aloEupiaWu0waDubgwAA4oqGLGUeZkhMwPEXvECQzQ5t7sIqxS5dmH4+YmkVcQFgHAQd4UnU1BYqNFNGxfFbLLEl-FhRhJVYeE7ogmCBEOtUMJwpGAZ6jdlVcQ9X13AFbhzdUXBodUqUulwGVAtlcXzTA3gBL4ANQvl8IIygxVpWoRYRbdf7IDwKTQqT6jU5Dd0oEWrV02ADNM15lBFt1En9SU3a7YjA6JCLYsoGKMgSwAjM2ADMAAso4mYEL2KrJ2ujtoCCgMGWv9oOck9tLEqKqb2tdDAmQLsNGmrrY2AWFA2AIAYcCWgYpOHqZJ7mat55WRt15ZLk0v7claCdhbirvhex3fjTqaBUBb0Vh9LOcWz0MUwhTIoCypOZ6RdTxyjn0FxjHp4XKlcrM+4baqT5No3WOe-qm0uyw1HHd+zEStb3Mh81AAtB0LUmi4qssK8rKuqepo3OzYjgoKSEDeDAABSEBAbNiq2PrhuByt-ytckzwRzkUfue96Cdt1cAQABUAV3PMiJ1ZycVbntMLpl1YtdFOrNRD5zRg8J4pdR6o3glTCGADUy1RJGSLuxpIHwXiFYZCKBS4vzftAT+1RZbwO+u6Z4SQZAKEcoTY+koj7VHbggv+jVwE9y-v3PyUVh7rWxqQseR0IiCxgF2bsHtgAbygK-d+U4ADqAAJMUjkcgACFtwKDgAAaRHObL+C9VbL0dqvLSNgzBSNEsXWAwBsAe0ID+f2x5uqX34TZOyDknK5CMOPNhA9-IwBAJ7PAcxQH-0HqaR6hdgnsnIelH6mVbL2ToSgGIjhbRAXYJobQRYYoIUGnkxBYDkFMTamg7hp0858NDmU+qwjwiiL6sUQa6kgA)https://sequencediagram.org/index.html#initialData=IYYwLg9gTgBAwgGwJYFMB2YBQAHYUxIhK4YwDKKUAbpTngUSWDAEooDmSAzmFMARDQVqhFHXyFiwUgBF+wAIIgQKLl0wATeQCNgXFDA3bMmdlAgBXbDADEaYFQCerDt178kg2wHcAFkjAxRFRSAFoAPnJKGigALhgAbQAFAHkyABUAXRgAegt9KAAdNABvfMp7AFsUABoYXDVvaA06lErgJAQAX0xhGJgIl04ePgEhaNF4qFceSgAKcqgq2vq9LiaoFpg2joQASkw2YfcxvtEByLkwRWVVLnj2FDAAVQKFguWDq5uVNQvDbTxFgAUQUMhgi2WMAAZuZKjBXpRvphvkpflwBoMjm5Rp5xiIVPE0BYEAhDjMTnizip-qjbmp4iBpvwUIioO8KsBqnUGutml95Gi7v8jPEAJIAOTIwJY6QhHy5K15Gy2O06MEl6RSCIKyOxIw8gmpBkGdPRjOZgQUFjAvg5S0VAuuQr+g1FGqlMrlkMVdWANt86QgAGt0B6tTBrbbkWbhaEsRTcUaJoSYP7bUHQ2hycck-iqOd45E+pR4unAyH0L1opRMZFgugwPEAEwABlbxRKMAARD7qt3Yt3lt26t3y5n0APu5As92YD10BoTGZLNYbNBpI8YAAZCCcNA+fyBTANsLFmtxRKpDLZHL6NR4zt9pVrFU9EuwBO5w35yYwBB7kgaD2ssPKvvyOY4j+xq0oK9L3BC2BaFaAYgY6KJweiETupK0qyvKnLcmmAYTgemralGvgxphcZflBpwpigZYkZW2b6pSyYEiagwfsxGasdW1C1kW8DII2Lbtp2PbPlOw6juOrFTjOk7zpgi7LuYVi2GYKBhru7CWMwNh+AEQRiWeURCZeCQyMC27AukwK3veXCPvYLFZoJ-QROxebGvEAEGTacwKVmBy+dBjERLGDIwI8YCUSFHnoE6Px3NhgKsKC4KhWGsIQPClHUc68F1kM9FUox8QAITEqSkEGgxXF1rxMB1WSH51qeTYwG2HZoO1aloEupiaWu0waDubgwAA4oqGLGUeZkhMwPEXvECQzQ5t7sIqxS5dmH4+YmkVcQFgHAQd4UnU1BYqNFNGxfFbLLEl-FhRhJVYeE7ogmCBEOtUMJwpGAZ6jdlVcQ9X13AFbhzdUXBodUqUulwGVAtlcXzTA3gBL4ANQvl8IIygxVpWoRYRbdf7IDwKTQqT6jU5Dd0oEWrV02ADNM15lBFt1En9SU3a7YjA6JCLYsoGKMgSwAjM2ADMAAso4mYEL2KrJ2ujtoCCgMGWv9oOck9tLEqKqb2tdDAmQLsNGmrrY2AWFA2AIAYcCWgYpOHqZJ7mat55WRt15ZLk0v7claCdhbirvhex3fjTqaBUBb0Vh9LOcWz0MUwhTIoCypOZ6RdTxyjn0FxjHp4XKlcrM+4baqT5No3WOe-qm0uyw1HHd+zEStb3Mh81AAtB0LUmi4qssK8rKuqepo3OzYjgoKSEDeDAABSEBAbNiq2PrhuByt-ytckzwRzkUfue96Cdt1cAQABUAV3PMiJ1ZycVbntMLpl1YtdFOrNRD5zRg8J4pdR6o3glTCGADUy1RJGSLuxpIHwXiFYZCKBS4vzftAT+1RZbwO+u6Z4SQZAKEcoTY+koj7VHbggv+jVwE9y-v3PyUVh7rWxqQseR0IiCxgF2bsHtgAbygK-d+U4ADqAAJMUjkcgACFtwKDgAAaRHObL+C9VbL0dqvLSNgzBSNEsXWAwBsAe0ID+f2x5uqX34TZOyDknK5CMOPNhA9-IwBAJ7PAcxQH-0HqaR6hdgnsnIelH6mVbL2ToSgGIjhbRAXYJobQRYYoIUGnkxBYDkFMTamg7hp0858NDmU+qwjwiiL6sUQa6kgA)

## IntelliJ Support

Open the project directory in IntelliJ in order to develop, run, and debug your code using an IDE.

## Maven Support

You can use the following commands to build, test, package, and run your code.

| Command                    | Description                                     |
| -------------------------- | ----------------------------------------------- |
| `mvn compile`              | Builds the code                                 |
| `mvn package`              | Run the tests and build an Uber jar file        |
| `mvn package -DskipTests`  | Build an Uber jar file                          |
| `mvn install`              | Installs the packages into the local repository |
| `mvn test`                 | Run all the tests                               |
| `mvn -pl shared tests`     | Run all the shared tests                        |
| `mvn -pl client exec:java` | Build and run the client `Main`                 |
| `mvn -pl server exec:java` | Build and run the server `Main`                 |

These commands are configured by the `pom.xml` (Project Object Model) files. There is a POM file in the root of the project, and one in each of the modules. The root POM defines any global dependencies and references the module POM files.

### Running the program using Java

Once you have compiled your project into an uber jar, you can execute it with the following command.

```sh
java -jar client/target/client-jar-with-dependencies.jar

♕ 240 Chess Client: chess.ChessPiece@7852e922
```
