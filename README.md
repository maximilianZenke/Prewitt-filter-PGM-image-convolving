<h1>Readme</h1> <br>
<h2>Group "Spiel mir das Lied vom Code" - Zenke Prostka </h2>

<p>
We chose to complete exercise three using java because we think java does well handling file 
handling operations and has a very good support for object-oriented programming. We would have 
preferred to use Python ( as there were snippets of Python-code available ) but we already 
completed exercise two using Python. 
</p>

<b>Problem</b> <br>
<p>
We encountered a problem when we started implementing the "createBoxFilter()" method inside KernelFactory. 
The given UML clearly states that it should return a two dimensional array of integers, 
however the boxFilter requires decimal numbers ( f.e. every Element in 3x3 boxFilter has a value of 1/9, 
which can not appropriately be represented as Integer ). As Java as a static typing language, we were forced to choose between 

</p>

<ol>
<li>Write createBoxFilter() using Integers and misrepresent its values </li>
<li>Write createBoxFilter() using doubles and interfere with the UML and many other functionalities using integers instead of doubles.</li>
</ol>

<p>Finally, we choose the best of both worlds: We implemented a boxFilter using a double[][] array, just like the UML stated, but implemented another boxFilter, using an int[][] array. The former cannot be used, because, as stated above, many other functionalities rely on integers. The latter can be used, but produces only black pictures, because its values will always be less than one and will therefore be cut to zero, because they are integers.</p> 

<p>In the end, we think that the createBoxFilter functionality does not go along well with Javas static typing. The former tutor was right, assuming that Python with its dynamic typing would be a better candidate for completing this task </p>

<b>Additional testing </b>
<br><br>
After completing all the test stated in the final exercise via setting up the Main class, we felt the urge to further test our algorithms. The paperwork only included one example we could use to check if our program worked accordingly ( by manually comparing pixels ). 
Therefore we searched the web for appropriate examples and found a nice tutorial for using the Prewitt filter ( https://www.tutorialspoint.com/dip/prewitt_operator.htm ). The tutorial included a reference picture which was being convolved two times, once via a horizontal kernel and once via a vertical kernel. We decided that we could test our convolve algirithm by downloading the reference picture and then convolving it just like the tutorial stated. If our outcome would match theirs, we would be right to assume that our algorithm works as intended. <br>

To implement this test, we created an additional environment in shape of a folder "TestingPictures", containing the reference picture and its two convolved surrogates and a "Testing" class in our source folder to hold the necessary commands to convolve said reference picture. <br> 
After completing our test we opened the two outcome pictures in GIMP and compared them to the outcome pictures on the website. Fortunately they matched. We therefore assume, that our algorithm works as intended. <br>

*If you want to assure that our testing lead to the described results, we recommend you re-run the main in the Testing class, open the output stored in the TestingPictures-folder with GIMP and compare it with the corresponding images on the website stated above.*
