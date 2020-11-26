# DP & UI quiz

## Singleton Design Pattern

Consider the following piece of code, which returns an httpClient for establishing a network connection:

```java
public class SwengHttpClientFactory {
    private static AbstractHttpClient httpClient;
    public static synchronized AbstractHttpClient getInstance() {
        if (httpClient == null) {
            httpClient = create();
        }
        return httpClient;
    }

    public static synchronized void setInstance(AbstractHttpClient instance) {
        httpClient = instance;
    }
...
```

Which of the following statements are true regarding this design? Select all that apply.

- [n] This is a standard example of the original GOF factory
- [n] This is a standard example of the static factory method
- [n] This is a standard example of Singleton
- [y] The class allows switching the concrete HttpClient returned
- [n] The SwengHttpClientFactory decides what type of HttpClient to return

Suppose you implement an iPhone game that uses [AirPlay](http://en.wikipedia.org/wiki/AirPlay) mirroring to display the image both on the phone's screen and on an external display. Thus, your code needs to manage two screens. Is the display manager of your application a good use case for the Singleton Design Pattern?

- [y] Yes
- [n] No

## Inheritance vs Proxy vs Adapter

Imagine you're writing an application that analyzes Facebook users' messages using natural language processing (NLP) in order to automatically discover the most important discussion topics. Your application uses two existing libraries: One library interfaces with the Facebook servers, extracts the content of a user post, encapsulates it into a `FacebookPost` object, and returns this object to the caller. The other library takes in an `NLPDocument` object, automatically extracts the most relevant topics from the respective document, and returns them to the caller.

Of the following four options, which is the one most effective way of building the desired application?

- [n] Write an `NLPFacebookPost` class that subclasses `FacebookPost` and also implements `NLPDocument`'s interface, then change the NLP library to accept `NLPFacebookPost` objects instead of `NLPDocument` objects.
- [y] Write a `FacebookPostNLPDocumentAdapter` class that adapts the interface of `FacebookPost` to the `NLPDocument` interface
- [n] Write an `NLPDocumentFacebookPostAdapter` class that adapts the interface of `NLPDocument` to the `FacebookPost` interface
- [n] Use the Proxy design pattern to make `FacebookPost` objects behave like `NLPDocument` objects

## MVC

The following pseudo-code is a sketch of an online students management application that uses the MVC pattern.

```java
class StudentsDatabase {
    List<Student> mStudents;

    //...

    List<Student> getStudents();
}
class StudentsManager implements WebPage {
    @Override
    public String toHtml() {
        //...
        for (Student s : mDb.getStudents()) {
            toReturn = toReturn + s.name + "<br/>";
        }
        return toReturn;
    }
}

class WebApplication {
    public String getRequest(String url) {
        //...
        WebPage page = new StudentsManager(...);
        //...
        return page.toHtml();
    }
}
```

Which of the following statements is (are) true?

- [n] `StudentsDatabase` is the model, `StudentsManager` is the controller, and `WebApplication` is the the view.
- [y] `StudentsDatabase` is the model, `StudentsManager` is the view, and `WebApplication` is the controller.
- [n] `StudentsManager` is the model, `StudentsDatabase` is the view, and `StudentsManager` is the controller.
- [n] This is not MVC, because `StudentsManager` must use a listener to be notified when the database changes.
