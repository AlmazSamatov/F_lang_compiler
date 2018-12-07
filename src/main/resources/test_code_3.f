fibb is func(v: integer): integer do
    if (v = 1) | (v = 2) then
        return 1
    else
        return fibb(v-1) + fibb(v-2)
    end
end;

number: integer is 8;

calculateFibb is func() do
    print number, "-th fibbonacci number is ", fibb(number)
end;

main is calculateFibb()