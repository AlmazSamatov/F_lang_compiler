g is func(x: integer) => x + 1;
h is func(x: integer) => x * x;
f is func(y: integer) do x is 5 return g(x) + h(x) end;

main is func(): integer do
    print f(5)
    return 0
end;

res is main()