
module Main where

import Test.HUnit
import System.Exit

-- Problem 2
fibonacci :: Int -> Int
fibonacci 0 = 0
fibonacci 1 = 1
fibonacci n = fibonacci (n-1) + fibonacci (n-2)

-- Problem 3
myProduct :: [Integer] -> Integer
myProduct (n:ns) = n * myProduct ns

-- Problem 4
flatten :: [[a]] -> [a]
flatten (x:xs) = x ++ flatten xs

-- Problem 5
myLength :: [a] -> Int
myLength [] = 0
myLength (_:xs) = 1 + myLength xs

-- Problem 6
quicksort :: Ord t => [t] -> [t]
quicksort [] = []
quicksort (x:xs) = quicksort smaller ++ [x] ++ quicksort larger
  where 
    smaller = [a | a <- xs, a <= x]
    larger = [b | b <- xs, b > x]

-- Problem 7
isElement :: Eq a => a -> [a] -> Bool
isElement x [] = False
isElement x (y:xs) = if x == y then True else isElement x xs

-- Problem 8
evenNumbersOut :: Integral a=> [[a]] -> [[a]]
evenNumbersOut = undefined

-- Problem 9
gpaAdvice :: Double -> Double -> [Char]
gpaAdvice x xs 
                | (x/xs) > 3.5 = "Good Job"
                | (x/xs) < 2.5 = "Do better"
                | otherwise = "Okay"

-- Problem 10
perfects :: Int -> [Int]
perfects n = [x|x<-[1..n], x==sum(factors(x))]

factors :: Int -> [Int]
factors n = [x|x<-[1..n-1], n `mod` x==0]

-- Problem 11
replicateThis :: Int -> a -> [a]
replicateThis 0 _ = []
replicateThis n x = x : replicateThis (n-1) x



myTestList = 
  TestList [ 
    "fibonacci" ~: fibonacci 4 ~=? 3

    , "myProduct" ~: myProduct [1..10] ~=? 3628800
    
    , "flatten 1" ~: flatten [[]::[Int]] ~=? []
    , "flatten 2" ~: flatten [[]::[Int], [], []] ~=? []
    , "flatten 3" ~: flatten [[1], [2, 3, 4], [], [5, 6]] ~=? [1, 2, 3, 4, 5, 6]
      
    , "myLength" ~: myLength [1, 2, 3] ~=? 3

    , "quicksort 1" ~: quicksort [3, 2, 5, 1, 6] ~=? [1,2,3,5,6]
    , "quicksort 2" ~: quicksort "howdy" ~=? "dhowy"
    
    , "isElement 1" ~: (isElement 'c' "abcd") ~=? True
    , "isElement 2" ~: (isElement 'e' "abcd") ~=? False
      
   
    ]

main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          exitWith (codeGet errs fails)
          
codeGet errs fails
 | fails > 0       = ExitFailure 2
 | errs > 0        = ExitFailure 1
 | otherwise       = ExitSuccess
