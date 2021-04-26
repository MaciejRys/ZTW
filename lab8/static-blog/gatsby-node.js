const path = require('path')
const { createFilePath } = require(`gatsby-source-filesystem`)
const { slash } = require(`gatsby-core-utils`)

exports.onCreateNode = ({ node, getNode, actions }) => {
  const { createNodeField } = actions
  
  if (node.internal.type === `MarkdownRemark`) {
    const slug = createFilePath({ node, getNode, basePath: `pages` })
    createNodeField({
      node,
      name: `slug`,
      value: slug,
    })
  }
}

exports.createPages = async ({ graphql, actions }) => {
    const { createPage } = actions

    const {
        data: {
          allWpPost: { nodes: allPosts },
        },
      } = await graphql(`
        query {
          allWpPost {
            nodes {
              id
              uri
            }
          }
        }
      `)
      const postTemplate = path.resolve(`./src/templates/wp-posts.js`)
      allPosts.forEach(post => {
        createPage({
          // will be the url for the page
          path: post.uri,
          // specify the component template of your choice
          component: slash(postTemplate),
          // In the ^template's GraphQL query, 'id' will be available
          // as a GraphQL variable to query for this post's data.
          context: {
            id: post.id,
          },
        })
      })

    return new Promise((resolve, reject) => {
        graphql(`
            {
                allMarkdownRemark {
                    edges {
                        node {
                            fields {
                                slug
                            }
                        }
                    }
                }
            }
        `).then(result => {
            result.data.allMarkdownRemark.edges.forEach(({ node }) => {
                createPage({
                    path: node.fields.slug,
                    component: path.resolve(`./src/templates/posts.js`),
                    context: {
                        slug: node.fields.slug,
                    },
                })
            })
            resolve()
        })
    })
}
