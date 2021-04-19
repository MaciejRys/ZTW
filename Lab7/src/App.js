// JavaScript source code
const axios = require("axios");


var mysql = require('mysql');

var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: ""
});

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
});

var sql2 = "use ZTW";
con.query(sql2, function (err, result) {
    if (err) throw err;
});
var usersList = [];
var todosList = [];
async function getRestUsersList() {
    try {

        const users = await axios.get("https://jsonplaceholder.typicode.com/users");

        usersList = users.data.map(({ id, name, email, username }) => ({
            id: id,
            name: name,
            email: email,
            login: username,
        }))
        for (var i = 0; i < usersList.length; i++){
            var sql = `INSERT INTO users (name, email, login) VALUES ( '${usersList[i].name}', '${usersList[i].email}', '${usersList[i].login}');`;
            con.query(sql, function (err, result) {
                if (err) throw err;
            });

        }

    } catch (error) {
        throw error
    }
}

async function getRestTodosList() {
    try {
        const todos = await axios.get("https://jsonplaceholder.typicode.com/todos");

        todosList = todos.data.map(({ userId, id, title, completed }) => ({
            id: id,
            title: title,
            completed: completed,
            user_id: userId,
        }))

        for (var i = 0; i < todosList.length; i++){
            var sql = `INSERT INTO todos (title, completed, userId) VALUES ( '${todosList[i].title}', '${todosList[i].completed}', '${todosList[i].user_id}');`;
            con.query(sql, function (err, result) {
                if (err) throw err;
            });

        }
    } catch (error) {
        throw error
    }
} 
getRestUsersList();
getRestTodosList();

const { GraphQLServer } = require('graphql-yoga');
const resolvers = {
    Query: {
        users: () => allUsers().then(function(results){return results}),
        todos: () => allTodos().then(function(results){return results}),
        todo: (parent, args, context, info) => todoById(parent, args, context, info).then(function(results){return results}),
        user: (parent, args, context, info) => userById(parent, args, context, info).then(function(results){return results}),
    },
    User: {
        todos: (parent, args, context, info) => todosOfUser(parent, args, context, info).then(function(results){return results})
    },
    ToDoItem: {
        user: (parent, args, context, info) => userOfTodo(parent, args, context, info).then(function(results){return results})
    },
    Mutation: {
        addToDo: (parent, args, context, info) => addToDo(parent, args, context, info).then(function(results){return results}),
        updateToDo: (parent, args, context, info) => updateToDo(parent, args, context, info).then(function(results){return results}),
        removeToDo: (parent, args, context, info) => removeToDo(parent, args, context, info).then(function(results){return results}),
        addUser: (parent, args, context, info) => addUser(parent, args, context, info).then(function(results){return results}),
        updateUser: (parent, args, context, info) => updateUser(parent, args, context, info).then(function(results){return results}),
        removeUser: (parent, args, context, info) => removeUser(parent, args, context, info).then(function(results){return results}),

    }
}

allTodos = function(){
    return new Promise(function(resolve, reject){
        con.query(`SELECT * FROM todos;`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve(rows);
                }
            }
        )}
    )}
allUsers = function(){
    return new Promise(function(resolve, reject){
            con.query(`SELECT * FROM users;`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve(rows);
                }
            }
        )}
    )}




function addToDo(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`INSERT INTO todos (title, completed, userId) VALUES ( '${args.ToDo.title}', '${args.ToDo.completed}', '${args.ToDo.userId}');`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve("Done");
                }
            }
        )}
    )
}

function updateToDo(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`UPDATE todos SET title = '${args.ToDo.title}', completed = '${args.ToDo.completed}', userId = '${args.ToDo.userId}' WHERE id = '${args.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error(err));
                }else{
                    resolve("Done");
                }
            }
        )}
    )
}

function removeToDo(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`DELETE FROM todos WHERE id = '${args.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve("Done");
                }
            }
        )}
    )
}

function addUser(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`INSERT INTO users (name, email, login) VALUES ( '${args.User.name}', '${args.User.email}', '${args.User.login}');`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve("Done");
                }
            }
        )}
    )
}


function updateUser(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`UPDATE users SET name = '${args.User.name}', email = '${args.User.email}', login = '${args.User.login}' WHERE id = '${args.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error(err));
                }else{
                    resolve("Done");
                }
            }
        )}
    )
}

function removeUser(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`DELETE FROM users WHERE id = '${args.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve("Done");
                }
            }
        )}
    )
}


function todoById(parent, args, context, info) {
    return new Promise(function(resolve, reject){
        con.query(`SELECT * FROM todos WHERE id = '${args.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve(rows[0]);
                }
            }
        )}
    )
}

function userById(parent, args, context, info) {
    return new Promise(function(resolve, reject){
        con.query(`SELECT * FROM users WHERE id = '${args.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve(rows[0]);
                }
            }
        )}
    )
}
function todosOfUser(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`SELECT * FROM todos WHERE userId = '${parent.id}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve(rows);
                }
            }
        )}
    )
}
function userOfTodo(parent, args, context, info){
    return new Promise(function(resolve, reject){
        con.query(`SELECT * FROM users WHERE id = '${parent.userId}';`,
            function(err, rows){
                if(rows === undefined){
                    reject(new Error("Error rows is undefined"));
                }else{
                    resolve(rows[0]);
                }
            }
        )}
    )
}


const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
});

server.start(() => console.log(`Server is running on http://localhost:4000`)); 