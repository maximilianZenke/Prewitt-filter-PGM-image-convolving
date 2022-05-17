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
which can not appropriately be represented as Integer ). As Java as a static typing language, we were forced to to find a solution that is in line with both, the UML and our needs.
</p>

<p> Luckily, we found a way to satisfy all sides:</p>
<ul>
<li>
We implemented createBoxfilter using integers ( to satisfy the UML ). This method compiles but leads to incorrect results.
</li>
<li>
We implemented createBoxfilter using doubles ( to satisfy the functionalities inherent need for doubles ). This method does compile but cannot be used because many other functionalities rely on integers. 
</li>
<li>
We found a way to somewhat work dynamically with our values, using the java Number class. We implemented extra methods, to show how this solves our problem and added additional test-cases to our main. Using this approach we managed to use the boxFilter as intended. As seen inside the Clamping-Folder, our Numbers-solution leads to good results. We therefore consider this a good solution to our problem.
</li>
</ul>

<p>In the end, we think that the createBoxFilter functionality does not go along well with Javas static typing. The former tutor was right, assuming that Python with its dynamic typing would be a better candidate for completing this task </p>

<b>Additional testing </b>
<br><br>
After completing all the test stated in the final exercise via setting up the Main class, we felt the urge to further test our algorithms. The paperwork only included one example we could use to check if our program worked accordingly ( by manually comparing pixels ). 
Therefore we searched the web for appropriate examples and found a nice tutorial for using the Prewitt filter ( https://www.tutorialspoint.com/dip/prewitt_operator.htm ). The tutorial included a reference picture which was being convolved two times, once via a horizontal kernel and once via a vertical kernel. We decided that we could test our convolve algirithm by downloading the reference picture and then convolving it just like the tutorial stated. If our outcome would match theirs, we would be right to assume that our algorithm works as intended. <br>

To implement this test, we created an additional environment in shape of a folder "TestingPictures", containing the reference picture and its two convolved surrogates and a "Testing" class in our source folder to hold the necessary commands to convolve said reference picture. <br> 
After completing our test we opened the two outcome pictures in GIMP and compared them to the outcome pictures on the website. Fortunately they matched. We therefore assume, that our algorithm works as intended. <br>

*If you want to assure that our testing lead to the described results, we recommend you re-run the main in the Testing class, open the output stored in the TestingPictures-folder with GIMP and compare it with the corresponding images on the website stated above.*
