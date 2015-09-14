# Homework 0 - Due: 9/16 
The purpose of this homework is to help you familiarize yourself with GitHub and Jarvis, and allow you to see what Jarvis does through a tiny programming assignment.

## GitHub and Jarvis Setup

Follow the guide here [https://github.com/cs3134/admin/blob/master/course.md](https://github.com/cs3134/admin/blob/master/course.md) to setup GitHub and Jarvis.

## Programming Assignment

In the folder `0\src` (the folder for Homework 0), you should see a file called `Jarvis3134.java`.

```Java
public class Jarvis3134 {

    /**
     * Edit this to return 3134 instead of 1007 ;)
     * 
     * @return
     */
    public static int sayHi() {
        return 1007;
    }
}
```

This is a simple file that contains a static method that returns the number `1007`. That's not quite right! You're in CS3134, not 1007. So let's change the code to return that instead.

Your task is to **edit the function** `sayHi()` to return `3134`.

Make the changes to the source code, then in the folder `0`, execute the following commands:

```bash
$ git add --all
$ git commit -m "homework 0 completed"
$ git push
```

If you set up your GitHub and Jarvis correctly, this should go through with no errors and you should have an email from Jarvis showing your grades (hopefully 100!) in a few minutes.

Feel free to change the commit message to anything else, but you'd thank yourself for putting in something other than `omgomgomg it finally works?!` or `plsplspls let it work` because Git allows you to revert to previous commits really easily (and you'd want to identify which one you want to revert to without combing through every single one).
