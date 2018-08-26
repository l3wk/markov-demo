# Markov Chain Demo 
A simple application demonstrating how to use Markov chains to transform the contents of a text file.

## Overview
This demo application uses a Markov chain algorithm to transform the contents of a text file. The application provides a web UI which can be used to upload the text file to convert and specify settings to customise the transformation.

## Requirements
- Java 1.8+
- Maven 3

## How To Run
Import the code into your IDE, and run *MarkovDemoApplication.java*. In a web browser, navigate to the following URL:

```bash
http://localhost:8080
```

Select a file to upload (e.g., *jabberwocky.txt* as found in the repository *resources* directory) and specify the prefix length (e.g., *1* or *2*, etc) and the (maximum) number of suffixes (or words) to generate (e.g., *100*).

## Future Work
Some ideas for future enhancements include:
- Use CSS stylesheets to improve the appearance of the web UI
- Implement additional tests to further exercise the implementation of the Markov chain algorithm
- Detect generation of empty results, and automatically re-run the algorithm
- Containerize the service 
