import * as React from "react"
import { graphql, Link } from "gatsby"
import './index.css'
import TemplateWrapper from '../layouts/index'

const IndexPage = ({ data }) => {
  console.log(data)
  return (
    <TemplateWrapper>
      <div>
        <div>
          {data.allMarkdownRemark.edges.map(({ node }) => (
            <div key={node.id} className="article-box">
              <Link to={node.fields.slug} style={{ textDecoration: 'none', color: "inherit" }}>
                <h3 className="title">{node.frontmatter.title}</h3>
              </Link>
              <p className="author">Author: <i>{node.frontmatter.author}</i></p>
              <p className="date">{node.frontmatter.date} {node.timeToRead}min read</p>
              <p className="excerpt">{node.excerpt}</p>
            </div>
          ))}
        </div>
        <div>
          {data.allWpPost.edges.map(({ node }) => (
            <div key={node.id} className="article-box">
              <Link to={node.uri} style={{ textDecoration: 'none', color: "inherit" }}>
                <h3 className="title">{node.title}</h3>
                <p className="author">Author: <i>{node.author.firstName} {node.author.lastName}</i></p>
                <p className="date">{node.date}</p>
              </Link>
            </div>
          ))}
        </div>
      </div>
    </TemplateWrapper>
  )
}

export default IndexPage

export const query = graphql`
query HomePageQuery{
  allMarkdownRemark(sort: {fields: [frontmatter___date], order: DESC}) {
    totalCount
    edges {
      node {
        fields {
          slug
        }
        frontmatter {
          title
          date
          author
        }
        excerpt
        timeToRead
      }
    }
  }
  allWpPost {
    edges {
        node {
          author {
            node {
              firstName
              lastName
            }
          }
          content
          date
          uri
          id
          title
        }
    }
}
}
`