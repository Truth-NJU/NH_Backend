共分为三层：vo、dto、po
1. vo负责和前端进行数据交互
2. vo在controller层转成dto，service层用来处理dto（可以将dto转化成po后存入数据库，也可以将从数据库中读取到的po类型的数据转化成dto）