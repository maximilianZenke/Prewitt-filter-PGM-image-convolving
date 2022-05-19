<h1>Readme</h1> <br>
<h2>Software artifact for course "Informatik 3" at Reutlingen University</h2>

<p>
I chose to complete exercise three using java because java does well in file 
handling operations and has a very good support for object-oriented programming. 
</p>

<b>Problem</b> <br>
<p>
I encountered a problem during implementing the "createBoxFilter()" method inside KernelFactory. 
The given UML clearly states that it should return a two dimensional array of integers, 
however the boxFilter requires decimal numbers ( f.e. every Element in 3x3 boxFilter has a value of 1/9, 
which can not appropriately be represented as Integer ). As Java is a static typing language, I was forced to to find a solution that is in line with both, the UML and my needs.
</p>

<p> Luckily, I found a way to satisfy all sides through implementing the following (additional) solutions:</p>
<ul>
<li>
A createBoxfilter using integers ( to satisfy the UML ). This method compiles but leads to incorrect results.
</li>
<li>
A createBoxfilter using doubles ( to satisfy the functionalities inherent need for doubles ). This method does compile but cannot be used because many other functionalities rely on integers. 
</li>
<li>
I found a way to somewhat work dynamically with our values, using the java Number class. I implemented extra methods, to show how this solves the problem and added additional test-cases to our main. Using this approach I managed to use the boxFilter as intended. As seen inside the Clamping-Folder, my Numbers-solution lead to good results. I therefore consider this a good solution.
</li>
</ul>

<b>Additional testing </b>
<br><br>
After completing all the test stated in the final exercise via setting up the Main class, I felt the urge to further test my algorithms. The paperwork only included one example that could be used to check if my program workes accordingly ( by manually comparing pixels ). 
Therefore I searched the web for appropriate examples and found a nice tutorial for using the Prewitt filter ( https://www.tutorialspoint.com/dip/prewitt_operator.htm ). The tutorial included a reference picture which was being convolved two times, once via a horizontal kernel and once via a vertical kernel. I decided that I could test our convolve algirithm by downloading the reference picture and then convolving it just like the tutorial stated. If the outcome would match theirs, I would assume that my algorithm works as intended. <br>

To implement this test, I created an additional environment in shape of a folder "TestingPictures", containing the reference picture and its two convolved surrogates and a "Testing" class in the source folder to hold the necessary commands to convolve said reference picture. <br> 
After completing the test I opened the two outcome pictures in GIMP and compared them to the outcome pictures on the website. Fortunately they matched. I therefore assume, that my algorithm works as intended. <br>

*If you want to assure that our testing lead to the described results, I recommend you re-run the main in the Testing class, open the output stored in the TestingPictures-folder with GIMP and compare it with the corresponding images on the website stated above.*
