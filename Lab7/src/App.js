// JavaScript source code
const axios = require("axios");
var usersList = [];
var todosList = [];
async function getRestUsersList() {
    try {
        const users = await axios.get("https://jsonplaceholder.typicode.com/users")
        usersList = users.data.map(({ id, name, email, username }) => ({
            id: id,
            name: name,
            email: email,
            login: username,
        }))
    } catch (error) {
        throw error
    }
} 

async function getRestTodosList() {
    try {
        const todos = await axios.get("https://jsonplaceholder.typicode.com/todos")
        todosList = todos.data.map(({ userId, id, title, completed }) => ({
            id: id,
            title: title,
            completed: completed,
            user_id: userId,
        }))
    } catch (error) {
        throw error
    }
} 
getRestUsersList();
getRestTodosList();



const { GraphQLServer } = require('graphql-yoga');
const resolvers = {
    Query: {
        users: () => usersList,
        todos: () => todosList,
        todo: (parent, args, context, info) => todoById(parent, args, context, info),
        user: (parent, args, context, info) => userById(parent, args, context, info), 
    },
    User: {
        todos: (parent, args, context, info) => {
            return todosList.filter(t => t.user_id == parent.id);
        }
    },
    ToDoItem: {
        user: (parent, args, context, info) => {
            return usersList.find(u => u.id == parent.user_id);
        }
    },
    Mutation: {
        addToDo: (parent, args, context, info) => addToDo(parent, args, context, info),
        updateToDo: (parent, args, context, info) => updateToDo(parent, args, context, info),
        removeToDo: (parent, args, context, info) => removeToDo(parent, args, context, info),
        addUser: (parent, args, context, info) => addUser(parent, args, context, info),
        updateUser: (parent, args, context, info) => updateUser(parent, args, context, info),
        removeUser: (parent, args, context, info) => removeUser(parent, args, context, info),

    }
}

function addToDo(parent, args, context, info){
    const lastTodo = todosList[todosList.length - 1];
    const ToDo = {id: lastTodo.id + 1,title: args.ToDo.title, completed: args.ToDo.completed, user_id: args.ToDo.userId }
    todosList.push(ToDo)
    return ToDo;
}

function updateToDo(parent, args, context, info){
    const Todo = todosList.find(t => t.id == args.id);
    Todo.title = args.ToDo.title;
    Todo.completed = args.ToDo.completed;
    Todo.user_id = args.ToDo.userId;
    return Todo;
}

function removeToDo(parent, args, context, info){
    const Todo = todosList.find(t => t.id == args.id);
    const index = todosList.indexOf(Todo);
    if (index > -1) {
        todosList.splice(index, 1);
    }
    return Todo;
}

function addUser(parent, args, context, info){
    const lastUser = usersList[usersList.length - 1];
    const User = {id: lastUser.id + 1,name: args.User.name, email: args.User.email, login: args.User.login }
    usersList.push(User)
    return User;
}


function updateUser(parent, args, context, info){
    const User = usersList.find(t => t.id == args.id);
    User.name = args.User.name;
    User.email = args.User.email;
    User.login = args.User.login;
    return User;
}

function removeUser(parent, args, context, info){
    const User = usersList.find(t => t.id == args.id);
    const index = usersList.indexOf(User);
    if (index > -1) {
        usersList.splice(index, 1);
        return User;
    }
}


function todoById(parent, args, context, info) {
    return todosList.find(t => t.id == args.id);
}
function userById(parent, args, context, info) {
    return usersList.find(u => u.id == args.id);
} 

const server = new GraphQLServer({
    typeDefs: './src/schema.graphql',
    resolvers,
});

server.start(() => console.log(`Server is running on http://localhost:4000`)); 