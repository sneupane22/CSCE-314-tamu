module Main where

import Prelude hiding (lookup)

import Test.HUnit
import System.Exit

-- Haskell data types for W
data WValue = VInt Int 
            | VBool Bool
            | VMarker
              deriving (Eq, Show)

data WExp = Val WValue
          | Var String

          | Plus WExp WExp
          | Mult WExp WExp

          | Equals      WExp WExp
          | NotEqual    WExp WExp
          | Less        WExp WExp
          | Greater     WExp WExp
          | LessOrEq    WExp WExp
          | GreaterOrEq WExp WExp

          | And  WExp WExp
          | Or   WExp WExp
          | Not  WExp
            deriving Show

data WStmt = Empty
           | VarDecl String WExp
           | Assign  String WExp
           | If      WExp   WStmt WStmt
           | While   WExp   WStmt
           | Block  [WStmt]
             deriving Show

type Memory = [(String, WValue)]
marker = ("|", VMarker)
isMarker (x, _) = x == "|"

-- eval
-- Same thing in project 3
eval :: WExp -> Memory -> WValue
eval (Val a) d = a
eval (Var a) d | lookup a d == Nothing = error $"Unknown Var"++ a
               | otherwise = eval (Val (fromJust(lookup a d))) d
eval ((Plus a b )) d = VInt ((asInt (eval a d)) + (asInt (eval b d)))
eval (Plus _ _) d = error "ERROR Plus"
eval (Mult a b) d = VInt ((asInt (eval a d) ) * (asInt (eval b d)))
eval (Mult _ _) d = error "ERROR Mult"
eval (Equals a b) d = VBool ((asInt (eval a d)) == (asInt (eval b d)))
eval (Equals _ _) d = error "ERROR Equals"
eval (NotEqual a b) d = VBool ((asInt (eval a d)) /= (asInt (eval b d)))
eval (NotEqual _ _) d = error "ERROR Not Equal"
eval (Less a b) d = VBool ((asInt (eval a d)) < (asInt (eval b d)))
eval (Less _ _) d = error "ERROR Less"
eval (Greater a b) d = VBool ((asInt (eval a d)) > (asInt (eval b d)))
eval (Greater _ _) d = error "ERROR Greater"
eval (LessOrEq a b) d = VBool ((asInt (eval a d)) <= (asInt (eval b d)))
eval (LessOrEq _ _) d = error "ERROR LessorEq"
eval (GreaterOrEq a b) d = VBool ((asInt (eval a d)) >= (asInt (eval b d)))
eval (GreaterOrEq _ _) d = error "ERROR Greater or Eq"
eval (And (Val (VBool a)) (Val (VBool b) )) d = VBool ((asBool (eval (Val (VBool a) ) d)) && (asBool (eval (Val (VBool b) ) d)))
eval (And _ _) d = error "ERROR And"
eval (Or a b) d = VBool((asBool (eval a d)) || (asBool (eval b d)))
eval (Or _ _) d = error "ERROR or"
eval (Not a) d = VBool (not (asBool (eval a d)))
eval (Not _) d = error "ERROR Not"

-- exec function
-- Define the Statements
exec :: WStmt -> Memory -> Memory
exec Empty d = d
exec (Assign a b) d | lookup a d == Nothing = error "N/A"
                    | otherwise = replace a (eval b d) d
                    where replace _ _ [] = []
                          replace a b (x@(k,_):xs)| a == k = if same b x
                                                                then (k,b):xs
                                                                else error $"ERROR"++ a
                                                  | otherwise = x : replace a b xs
                          same v@(VInt _) x@(_,VInt _) = True
                          same v@(VBool _) x@(_,VBool _) = True
                          same _ _ = False
exec (VarDecl a b) d | lookup a d == Nothing = (a, eval b d):d
                     | otherwise = error "Was declared before"                   
exec (If w x1 x2) d | eval w d == VBool(True) = exec x1 d
                    | otherwise = exec x2 d
exec (While i k) d | eval i d == VBool(True) = exec (While i k) (exec k d)
                   | eval i d == VBool(False) = d
                   | otherwise = error "ERROR"                                
exec (Block xs) d = popM $ foldr exec (marker:d) (reverse xs)
                  where popM [] = []
                        popM (x:xs) | isMarker x = xs
                                         | otherwise = popM xs

fibonacci :: Int -> Int
fibonacci n = asInt (fromJust avar)
                where avar = lookup "n" (exec nacci[("n", VInt(n))])


-- example programs
prog1 = Block
   [
     VarDecl "x" (Val (VInt 0)), 
     VarDecl "y" (Val (VInt 1)),
     VarDecl "b" (Greater (Var "x") (Val (VInt 0))),
     If (Or (Var "b") (Not (GreaterOrEq (Var "x") (Val (VInt 0)))))

        ( Block [ Assign "x" (Val (VInt 1)),
                  Assign "y" (Plus (Var "y") (Val (VInt 1)))
                ] 
        )
        ( Assign "x" (Val (VInt 2)) )
  ]

factorial = Block
  [
     VarDecl "acc" (Val (VInt 1)),
     While (Greater (Var "arg") (Val (VInt 0)))
     ( Block
       [ Assign "acc" (Mult (Var "acc") (Var "arg")),
         Assign "arg" (Plus (Var "arg") (Val (VInt (-1))))         
       ]
     ),
     Assign "result" (Var "acc")
  ]

nacci = Block
  [
      VarDecl "counter" (Val(VInt 1)),
      VarDecl "a" (Val(VInt 0)),
      VarDecl "b" (Val(VInt 1)),
      VarDecl "c" (Val(VInt 0)),
      While(LessOrEq (Var "counter")(Var "n"))
      (
        Block
        [
          Assign "c" (Plus (Var "a") (Var "b")),
          Assign "b" (Var "a"),
          Assign "a" (Var "c"),
          Assign "counter" (Plus (Var "counter") (Val (VInt (1))))
        ]
      ),
      Assign "n" (Var "c")
   ]

-- some useful helper functions
lookup s [] = Nothing
lookup s ((k,v):xs) | s == k = Just v
                    | otherwise = lookup s xs

asInt (VInt v) = v
asInt x = error $ "Expected NUM but got " ++ show x

asBool (VBool v) = v
asBool x = error $ "Expected BOOL but got " ++ show x

fromJust (Just v) = v
fromJust Nothing = error "Expected VAL - recieved MAYBE - got Nothing"

-- unit tests
myTestList =
  TestList [
    test $ assertEqual "prog1 test" [] (exec prog1 []),

    let res = lookup "result" (
               exec factorial [("result", VInt (-1)), ("arg", VInt 10)])
    in test $ assertBool "factorial of 10" (3628800 == asInt (fromJust res))
    ]    

-- main: run the unit tests  
main = do c <- runTestTT myTestList
          putStrLn $ show c
          let errs = errors c
              fails = failures c
          if (errs + fails /= 0) then exitFailure else return ()