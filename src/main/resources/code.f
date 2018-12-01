a is 5;
b is 7;

c is func(p: integer, t: integer): integer do
    res is p + p * t
    print res
    return res
end;

res is c(a, b)