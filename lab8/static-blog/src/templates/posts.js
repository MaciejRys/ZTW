import { graphql } from 'gatsby';
import React from 'react';

import { getImage, StaticImage } from "gatsby-plugin-image"
import TemplateWrapper from '../layouts/index'
import Img from "gatsby-image"

import { DiscussionEmbed } from "disqus-react"

export default ({ data }) => {
      const post = data.markdownRemark;
      let title = post.frontmatter.title;
        console.log(post);
      const disqusConfig = {
        shortname: 'lab8-1',
        config: { identifier: post.fields.slug, title },
      }
    let featuredImgFluid = undefined;
    if (post.frontmatter.featuredImage) {
        featuredImgFluid = post.frontmatter.featuredImage.childImageSharp.fluid;
    }

    return (
        <TemplateWrapper>
            <StaticImage src="../images/gatsby-astronaut.png" alt="An astronaut" />
            <div>
                <h1>{post.frontmatter.title}</h1>
                <h4 style={{ color: 'rgb(165, 164, 164)' }}>{post.frontmatter.author} <span style={{ fontSize: '0.8em' }}> -{post.frontmatter.date}</span></h4>
                <div dangerouslySetInnerHTML={{ __html: post.html }} />
                {featuredImgFluid && <Img fluid={featuredImgFluid} />}
            </div>
            <DiscussionEmbed {...disqusConfig} />
        </TemplateWrapper>
    );
};

export const query = graphql`
    query PostQuery($slug: String!) {
        markdownRemark(fields: { slug: { eq: $slug } }) {
            html
            fields {
                slug
            }
            frontmatter {
                title
                author
                date
                featuredImage {
                    childImageSharp {
                      fluid(maxWidth: 800) {
                        ...GatsbyImageSharpFluid
                      }
                    }
                }
            }
        }
    }
`;