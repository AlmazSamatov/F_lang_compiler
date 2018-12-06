max_value is func(arr: [integer]): integer do
    max is 0
    for i in arr loop
        if max < i then
            max := i
        end
    end
    return max
end;

main is func(): integer do
    max is max_value([1, 2, 3, 4, 7, 11, 5, 9])
    print "Max value is ", max
    return 0
end;

res is main()
