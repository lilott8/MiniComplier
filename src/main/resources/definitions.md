*Program*       --> *MainClass* *ClassDecl**

*MainClass*     --> __class__ *id* { __public__ __static__ __void__ __main__ ( __String__[] *id*)
                    { *Statement* } }
                    
*ClassDecl*     --> __class__ *id* { *VarDecl** *MethodDecl** }

                --> __class__ *id* __extends__ id' {*VarDecl** *MethodDecl**} 