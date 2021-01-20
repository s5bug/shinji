# shinji

shinji is a project to bring cats-like abstractions to other categories.

The name "shinji" comes from
[this Shinji](https://neutronized.fandom.com/wiki/Shinji), with a little
wordplay. I wanted to combine "C" (a category) and "cats" (the typelevel
library). "C" became "sea", and you can stretch from Shinji to "sea cat".

## purpose

This is not at all a replacement for cats, and should not be treated as such.
This library exists to facilitate development of structures that are narrower
than Scal (the category of Scala types) and as such it will be much less usable
than cats.

In the future, I may add some sort of cats interop: Anything with a
corresponding cats typeclass will get a Scal typeclass in shinji.

## goals

Many of my "original" ideas have just turned out to be variants on Compiling
to Categories (it turns out there are a lot of things that can be considered
categories). However, I noticed a lot of structures that I simply would not
be able to implement because of cats' focus on things specifically in Scal.
I decided that I would really like the benefits that these things give me
(especially things like Monad/Arrow syntax) without having to implement any
sort of runtime-checking to make sure I have the right types.

## what does any of this code mean?

I have no clue. I'm just copying what I see on nLab.
