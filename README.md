# ROLL THE BLOCK (OR BLOCKCHAIN)

Blockchains are immutable, sequential chain of records called Blocks. They can contain transactions, files or any data you like, really. But the important thing is that they’re chained together using hashes.

[Architecture is based on Bancolombia Scaffold](https://github.com/bancolombia/scaffold-clean-architecture) but some liberties were taken


## Summary 📋

- [Getting Started](#getting-started)
- [Runing the tests](#running-the-tests)
- [Deployment](#deployment)
- [Built With](#built-with)
- [Contributing](#contributing)
- [Versioning](#versioning)
- [Authors](#authors)
- [License](#license)
- [Acknowledgments](#acknowledgments)

## GettingStarted 🚀

These instructions will get you a copy of the project up and running on
your local machine for development and testing purposes. See deployment
for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

* Java 11
* IntelliJ is highly recommended

### Installing

A step by step series of examples that tell you how to get a development
env running

1. Clone repo
2. Open with IntelliJ 
3. Run the project
4. Import the Postman Collection
5. Run '1 Mine'

And you will get

```
{
    "data": {
        "content": {
            "message": "New Block Forged",
            "index": 2,
            "transactions": [
                {
                    "sender": "NodeMinerAddress",
                    "recipient": "60bf9a29e94e46fe9364c01d2ce535f7",
                    "amount": 1
                }
            ],
            "proof": 45972,
            "previousHsh": "84e2906ec60603ef54719c27b0c15769f6c6931007474eae8b0e4ff81cf0c33e"
        }
    }
}
```

## Running the tests

Use gradle to run tests

### Break down into end to end tests

    ~~Will be added later~~

## Deployment

You can use Heroku to deploy this project, remember to create
the required variables in Procfile

## Built With

- [Contributor Covenant](https://www.contributor-covenant.org/) - Used
  for the Code of Conduct
- [Creative Commons](https://creativecommons.org/) - Used to choose
  the license

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code
of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions
available, see the [tags on this
repository](https://github.com/cristianmarint/roll-the-block/tags).

## Authors

- **Cristian Marín** -
  [cristianmarint](https://github.com/cristianmarint)
- **Daniel van Flymen** -
  [Python approach codebase](https://github.com/dvf/blockchain-book) - [Article](https://hackernoon.com/learn-blockchains-by-building-one-117428612f46)

See also the list of
[contributors](https://github.com/cristianmarint/THIS.REPO/contributors)
who participated in this project.

## License

This project is licensed under the [CC0 1.0 Universal](LICENSE.md)
Creative Commons License - see the [LICENSE.md](LICENSE.md) file for
details

## Acknowledgments

- It's not perfect but the repo is open for improvement
