swap is func(l: integer, r: integer): integer do
    tmp is l
    l := r
    r := tmp
    return 0
end;

main is func(): integer do
    k is 5
    m is 7
    swap(k, m)
    print k, m
    return 0
end;

res is main()