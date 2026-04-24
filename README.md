<h1>MathFunction Object</h1>
A Java component repersenting polynomials as a sequence of double coefficients. Built on the OSU components/style.

## Functionality

MathFunction stores a polynomial in the form:

**f(X) = c1 + c2X +  c3X^2 + … cnX^(n-1): ∀c ∈ +ℝ**

 Method | Description |
|---|---|
| `add(int degree, double coeff)` | Adds `coeff` to the term at the given degree |
| `coefficient(int degree)` | Returns the coefficient at the given degree |
| `size()` | Returns the number of terms in the polynomial |
| `f(double x)` | Evaluates the polynomial at `x` |

### Secondary Methods

| Method | Description |
|---|---|
| `add(MathFunction a)` | Adds another polynomial to this one |
| `scale(double scalar)` | Multiplies every coefficient by `scalar` |
| `zeros(double a, double b)` | Returns approximate zeros over `[a, b]` |
| `relativeMaxs(double a, double b)` | Returns x-values of relative maxima over `[a, b]` |
| `relativeMins(double a, double b)` | Returns x-values of relative minima over `[a, b]` |
| `toString()` | Returns a string like `1.0 + 2.0x + 3.0x^2 + 4x^5` (skips zero terms) |


## Directory Structure

```text
│   .gitattributes
│   .gitignore
│   LICENSE
│   README.md
│
├───.vscode
│       extensions.json
│       osu-cse-checkstyle-config.xml
│       osu-cse-formatter.xml
│       settings.json
│
├───doc
│   │   README.md
│   │
│   ├───01-component-brainstorming
│   │       01-component-brainstorming.md
│   │
│   ├───02-component-proof-of-concept
│   │       02-component-proof-of-concept.md
│   │
│   ├───03-component-interfaces
│   │       03-component-interfaces.md
│   │
│   ├───04-component-abstract-class
│   │       04-component-abstract-classes.md
│   │
│   ├───05-component-kernel-implementation
│   │       05-component-kernel-implementation.md
│   │
│   └───06-component-finishing-touches
│           06-component-finishing-touches.md
│
├───lib
│       components.jar
│       hamcrest-core-1.3.jar
│       junit-4.13.2.jar
│       README.md
│
├───src
│   │   InnerArea.java
│   │   README.md
│   │   MSeries.java
│   │
│   └───components
│       └───mathfunction
│                       MathFunction.java
│                       MathFunctionSequence.java
│                       MathFunctionKernel.java
│                       MathFunctionSecondary.java
│
└───test
    │   README.md
    │
    └───components
        └───mathfunction
                        MathFunctionSequenceTest.java
                        MathFunctionTest.java
```


