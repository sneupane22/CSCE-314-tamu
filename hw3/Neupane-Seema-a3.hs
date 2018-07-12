
module Main where

import Test.HUnit
import System.Exit
import Data.List
import Data.Char


---- Part 1.
-- Problem 1

cutWhitespace :: [String] -> [String]
cutWhitespace zs = map (dropWhile isSpace) zs 

-- Problem 2

multListt :: Num a => [[a]] -> [[a]] -> [[a]]
multListt zs ys = map multi (zip zs ys)

multi x = zipWith(*) (fst x) (snd x)
--multListt = undefined

---- Part 2.
-- Problem 3
mergeBy :: (a -> a -> Bool) -> [a] -> [a] -> [a]
mergeBy _ [] y = y
mergeBy _ x [] = x
mergeBy f (x:xs) (y:ys) | f x y = x: mergeBy f xs(y:ys)
                        | otherwise = y: mergeBy f(x:xs) ys


halves xs = splitAt (length xs `div` 2) xs

msortBy :: (a -> a -> Bool) -> [a] -> [a]
msortBy _ [] = []
msortBy _ [x] = [x]
msortBy f x = mergeBy f (msortBy f a) (msortBy f b)
  where
      (a,b) = halves x

mergeSort :: Ord a => [a] -> [a]
mergeSort x = msortBy (<) x

-- Problem 4
multiply :: [Int] -> Int
multiply = foldr (*) 1
--multiply = undefined

-- Problem 5
concatenate :: [String] -> String
concatenate = foldl (++) ""     
--concatenate = undefined

-- Problem 6
concatenateAndUpcaseOddLengthStrings :: [String] -> String
concatenateAndUpcaseOddLengthStrings ys = map toUpper (concat (filter (odd . length) ys))

-- Problem 7
myInsert :: Ord a => a -> [a] -> [a]
myInsert p [] = [p]
myInsert p (x:xs) 
  | p <= x = p:x:xs
  | otherwise = x:myInsert p xs

insertionSort :: Ord a => [a] -> [a]
insertionSort = foldr myInsert []

-- Problem 8
{-- Foldr1 does not set a base case or a starting value--}
maxElem :: Ord a => [a] -> a 
maxElem xs = foldr1 max xs

---- Part 3.
data Tree a b = Branch b (Tree a b) (Tree a b)
              | Leaf a

-- Problem 9
instance (Show a, Show b) => Show (Tree a b) where
   show tree = showIndent "" tree
    where showIndent space (Leaf a) = space ++ show a ++ "\n"
          showIndent space (Branch b l t) = 
            space ++ (show b) ++ "\n" ++ 
            showIndent (space ++ "   ") l ++ 
            showIndent (space ++ "   ") t
   
-- Problem 10
preorder  :: (a -> c) -> (b -> c) -> Tree a b -> [c]
preorder f o (Leaf a) = [f a]
preorder f o (Branch b l r) = o b : preorder f o l ++ preorder f o r

inorder   :: (a -> c) -> (b -> c) -> Tree a b -> [c]
inorder f i (Leaf a) = [f a]
inorder f i (Branch b l r) = inorder f i l ++ [i b] ++ inorder f i r

---- Part 4. 
data E = IntLit Int
       | BoolLit Bool
       | Plus E E    
       | Mult E E    
       | Equals E E
         deriving (Eq, Show)

-- Problem 11
eval :: E -> E
eval d@(IntLit i) = d
eval d@(BoolLit b) = d
eval (Plus a b) = add (eval a) (eval b)
eval (Mult a b) = multip (eval a) (eval b)
eval (Equals a b) = eq (eval a) (eval b)

add (IntLit x) (IntLit y) = IntLit (x + y)
add _ _ = error "Addition ERROR"

multip (IntLit x) (IntLit y) = IntLit (x * y)
multip _ _ = error "Multiplication ERROR"

eq (IntLit x) (IntLit y) = BoolLit (x == y)
eq (BoolLit a) (BoolLit b) = BoolLit (a == b)
eq _ _ = error "Equals ERROR"


mytree = Branch "A" 
           (Branch "B" 
              (Leaf 1) 
              (Leaf 2)) 
           (Leaf 3)

prog1 = Equals 
           (Plus (IntLit 1) (IntLit 9))
           (Mult
              (IntLit 5)
              (Plus (IntLit 1) (IntLit 1)))

prog2 = Equals
           (Equals
              (Mult (IntLit 4) (IntLit 2))
              (Plus (IntLit 5) (Mult (IntLit 2) (IntLit 1))))
           (Equals (BoolLit True) (BoolLit True))

    
myTestList =

  let te s e a = test $ assertEqual s e a
      tb s b = test $ assertBool s b
  in
    TestList [ 
        
   

       te "mergeBy 1" "GFEDBA" (mergeBy (>) "FED" "GBA")
      , te "mergeBy 2" "HMaouiwdy" (mergeBy (<) "Howdy" "Maui")
      
      , te "msortBy 1" " 'eggim" (msortBy (<) "gig 'em") 
      , te "msortBy 2" "nmlkieecbbaJ  " (msortBy (>) "Jack be nimble")
      , te "msortBy 3" "" (msortBy (<) "")
      
      , te "mergeSort 1" " adhllowy" (mergeSort "howdy all") 
      , te "mergeSort 2" "" (mergeSort "") 
      , te "mergeSort 3" "x" (mergeSort "x") 

      , te "multiply" 10 (multiply [-2, -1, 5])
      
      , te "concatenate" "ABCD" (concatenate ["AB", "", "", "C", "D", ""])

      , te "concatenateAndUpcaseOddLengthStrings"
          "HERE'S AN EXAMPLE" (concatenateAndUpcaseOddLengthStrings ["here's ", "an ", "a ", "example"])

      , te "myInsert 1" "How are you?" (myInsert 'o' "Hw are you?")
      , te "myInsert 2" "abcdefg" (myInsert 'c' "abdefg")
      , te "insertionSort" "  Jabcceikkqu" (insertionSort "Jack be quick")
      
      , te "max in a list" 200 (maxElem [3, 10, 200, 42])

      , te "preorder" "AB123" (concatenate (preorder show id mytree))
      , te "inorder" "1B2A3" (concatenate (inorder show id mytree))

      , te "eval1" (BoolLit True) (eval prog1)
      , te "eval2" (BoolLit False) (eval prog2)
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
