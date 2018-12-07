fibb is func(v: integer): integer do
    if (v = 1) | (v = 2) then
        return 1
    else
        return fibb(v-1) + fibb(v-2)
    end
end;

v: integer is 3;

f is func() do
    print "Fibbonacci of ", v, " is ", fibb(v)
end;

main is f()