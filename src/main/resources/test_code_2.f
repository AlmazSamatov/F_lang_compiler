array is [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11];

max is array[0];

find_max is func(arr: [integer]) : integer do
    for i in arr loop
        if i > max then
            max := i
        end
    end
    return max
end;

main is func() do
    print "Max is ", find_max(array), "\n"
end;

res is main()