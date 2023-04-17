# Writing for Assignment&nbsp;3 Part&nbsp;2 2023 #

- **Name: Helen He**
- **Student ID: s2337142**
- **Tutorial group: 27**
- **Tutor's name: Neel Amonkar**
- **Today's date: 2023-04-09**



## Acknowledgements ##

### Java Tutorial ###

| **Website Link**                                                                                                                     | **Usage**                                                 |
|--------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| [W3School](https://www.w3schools.com/java/java_arraylist.asp)                                                                        | To check the basic method ArrayList contains              | 
| [StackOverFlow](https://stackoverflow.com/questions/1128723/how-do-i-determine-whether-an-array-contains-a-particular-value-in-java) | To get some ideas of how to compare items with Array      |
| [Xperti](https://xperti.io/blogs/how-to-format-string-in-java/)                                                                      | Learn how to format string                                |
| [TutorialsPoint](https://www.tutorialspoint.com/initializing-hashset-in-java)                                                        | Learn how to initialize hash set                          |
| PowerPoints from Object-Oriented-Programming lectures                                                                                | The basic instructions about Java has been helpful to me  |



## Code location ##

| **a3algorithms**      | **literatureStats**       | **Reason**                                                                                                              |
|-----------------------|---------------------------|-------------------------------------------------------------------------------------------------------------------------|
| `ExampleRunner`       | `Runner`                  | They have the same functions, both used to run the program                                                              |
| `Normaliser`          | `FrequencyWord`           | FrequencyWord has a method that has the same function as normaliser                                                     |
| `SimpleFrequencyWord` | `FrequencyWord`           | FrequencyWord contains the methods that SimpleFrequencyWord has. They share the same functionality                      |
| `AdvancedTextReader`  | `FrequencyDocumentReader` | The method "AdvancedReadFile" shares the same function as the method "readDocument" in FrequencyDocumentReader class    |
| `SortingOrder`        | `SortingOrder`            | They share the same functionality                                                                                       |
| `TrollSpeaker`        | `Translation`             | Some methods in Translation have the same functions as TrollSpeakers                                                    |
| `Verbosity`           | `Verbosity`               | They are both used to define the level of verbosity                                                                     |
| `VowelChecker`        | `Translation`             | VowelChecker was included in Translation class to check whether a character is a vowel                                  |


## DRY programming ##

DRY programming indicates Don't Repeat Yourself, which aimed at reducing 
the code repetition. 

In this assignment, there are a few examples,

1. Method `sortByValue` in `InformationDocument` class. This method takes 
a map and sorts it by value in ascending or descending order depends on
the parameter `order`. It can be used for any types of map, making the
code reusable. 

    A **WET version** of this could be writing separate methods 
for different map types, leading to code duplication.

2. `FrequencyDocumentPG` class. This class is a subclass of 
`FrequencyDocument`, which allows it to inherit all the methods and 
properties from its parent class. Thus, reduces the code repetition.

    A **WET version** could be creating a separate class without 
inheritance, resulting in rewriting common methods such as `initialise`, 
`countWords`.

3. `InformationDocument` constructors. The constructors of the 
`InformationDocument` class demonstrate the DRY principle by chaining 
together the constructors. Instead of duplicating the logic to initialize 
the doc object, it calls the `initialise` and `readDocument` methods, 
which are already defined in the `FrequencyDocument` class. This ensures 
that the initialization logic is written only once and reused across 
different constructors.
   
    A **WET version** of this would involve repeating the initialization 
logic in each constructor, which could lead to inconsistencies and 
maintenance issues.

To conclude, following the DRY principle helps create clean, maintainable 
code by reducing duplication and promoting code reuse.



## Relationships ##

1. `InformationDocument`, `FrequencyDocument` and `FrequencyDocumentPG`: 

   The `DataScientist` class has two instance variables of type 
`InformationDocument<FrequencyDocument>` and 
`InformationDocument<FrequencyDocumentPG>`. These are initialized in 
the `runExperiment1` and `runExperiment2` methods, respectively. 
Thus, `FrequencyDocument` and `FrequencyDocumentPG` interact with 
`DataScientist` indirectly via `InformationDocument` class.

2. `FrequencyWord`: 

   The `DataScientist` class uses the `FrequencyWord`class indirectly 
through the `InformationDocument` class. The `FrequencyWord` class is 
used in the `getTopNFrequencyWords` method of the `InformationDocument` 
class, which is then called by the `experiment1Phase3` and 
`experiment2Phase3` methods in `DataScientist`. This design choice 
encapsulates the word-frequency logic within the `FrequencyWord` class.

3. `Translation`: 

   The `DataScientist` class uses the `Translation` enumeration 
directly within methods `experiment1Phase1`, `experiment1Phase3`, 
`experiment2Phase1` and `experiment2Phase3`. It provides methods for 
translating words into different languages or dialects. The 
`DataScientist` class calls the `translate` method on the 
`Translation.DOG` enumeration instance to translate the words into 
the dog language.




## Explain your translate-to-dog implementation ##

There are two main rules on translating into the doggie language.
First of all, **"move the first non-vowel cluster to the end of the word"**.
In order to do this, I created an instance to store the index of the
first vowel. The input word then splits into "prefix" and "suffix" two 
parts according to the index. By swapping their order can easily deal with
the first rule. 

Secondly, there are some special cases where you append 
different postfix to the end of the word depending on the starting letter:
- If the first character is 'b', move the first non-vowel cluster to the 
end of the word and append "bark".
- If the first character is 'g', move the first non-vowel cluster to the 
end of the word and append "grrrowl" with 3 r's.
- If the first character is 'r', move the first non-vowel cluster to the 
end of the word and append "rrruf" with 3 r's.
- If the first character is 'w', check if the second character is 'o'. 
If it is, move the first non-vowel cluster to the end of the word and 
append "woofWoof". If the second character is not 'o', move the first 
non-vowel cluster to the end of the word and append "woof".
- For all other cases, move the first non-vowel cluster to the end of 
the word and append "ay".

Finally, it returns the translated word.


## Explain the enums ##

1. `SortingOrder`: 
In this method, enum has two constant values, ASCENDING and DESCENDING, 
which represent the two possible sorting orders. By using an enum for 
sorting order, the code becomes more readable and less prone to errors.
Enums also have a fixed set of values, which ensures that an invalid value 
cannot be passed as a sorting order.
2. `Translation`: 
This enum has three constant values, `NONE`, `TROLL`, and `DOG`. Each constant 
represents a different translation method for words. This design allows each 
constant to have a specific implementation for the translation process while 
sharing the same interface. It also makes it easier to add new translation 
methods in the future.

In summary, enums are used to represent sorting orders and translation methods, 
improving readability, type safety, encapsulation, and ease of maintenance. 
The code becomes more self-documenting, and the risk of errors due to 
incorrect values is minimized.
